package cmd.api;

import com.google.gson.Gson;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by pc1 on 11/28/2018.
 */
public class ApiRender {

    private static Map<String, List<Field>> fields = new ConcurrentHashMap<>();

    private static Map<String, List<Method>> methods = new ConcurrentHashMap<>();

    public static void clear(){
        fields.clear();
        methods.clear();
    }


    public void render(boolean updateToClient) {
        if (this.getClass().isAnnotationPresent(Api.class)) {
            ApiJsonRoot apiJsonRoot = new ApiJsonRoot();
            Class<?> entities[] = this.getClass().getAnnotation(Api.class).entities();
            for (Class<?> entity : entities) {
                if (entity.isAnnotationPresent(ApiEntity.class)) {
                   findAnnotation(entity, apiJsonRoot);
                }
            }

            try {
                if(updateToClient)
                    renderToJson(apiJsonRoot, this.getClass().getAnnotation(Api.class).name());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static void findAnnotation(Class<?> aClass, ApiJsonRoot apiJsonRoot){
        List<Field> listField = new ArrayList<>();
        List<Method> listMethod = new ArrayList<>();
        Class<?> c = aClass;
        while(c != null){
            render(c, listField, listMethod);
            c = c.getSuperclass();
        }

        fields.put(getApiName(aClass), listField);
        methods.put(getApiName(aClass), listMethod);

        ApiJsonType element = new ApiJsonType(aClass.getPackage().getName(), aClass.getSimpleName());
        for(Field field: listField){
            String type = field.getType().getSimpleName();
            if(type.equals("List") || type.equals("Set") || type.equals("Collection")){
                String generic = field.getGenericType().toString();
                generic = generic.substring(generic.indexOf("<")+1, generic.length()-1);
                generic = generic.substring(generic.lastIndexOf(".") + 1, generic.length());
                type = "Collection<" + generic + ">";
            }
            ApiJsonField api = new ApiJsonField(type, field.getName());
            element.field.add(api);
        }

        for(Method method: listMethod){
            String type = method.getReturnType().getSimpleName();
            if(type.equals("List") || type.equals("Set") || type.equals("Collection") || type.equals("Map")){
                String generic = method.getGenericReturnType().toString();
                generic = generic.substring(generic.indexOf("<")+1, generic.length()-1);
                generic = generic.substring(generic.lastIndexOf(".") + 1, generic.length());
                type = "Collection<" + generic + ">";
            }
            ApiJsonField api = new ApiJsonField(type, method.getName() + "()");
            element.field.add(api);
        }

        apiJsonRoot.api.add(element);
    }


//   private static void renderToXml(ApiXmlRoot apiXmlRoot) throws JAXBException {
//       File file = new File("D://project/ChessServer/res/xml/api.xml");
//       JAXBContext jaxbContext = JAXBContext.newInstance(ApiXmlRoot.class);
//
//       Marshaller marshaller = jaxbContext.createMarshaller();
//       marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
//       marshaller.marshal(apiXmlRoot, file);
//   }

    private static String getFullPath(String fileName) {
        String path = System.getProperty("user.dir");
        return path + "/src/main/java/cmd/api/" + fileName;
    }

    private static void renderToJson(ApiJsonRoot apiJsonRoot, String name) throws IOException {
        String path = getFullPath(name + ".json");
        File file = new File(path);
        Gson gson = new Gson();
        String json = gson.toJson(apiJsonRoot);

        FileWriter wr = new FileWriter(file);
        wr.write(json);
        wr.flush();
        wr.close();
    }

    private static void render(Class<?> aClass, List<Field> listField, List<Method> listMethod){
        List<Field> subListField = new ArrayList<>();
        List<Method> subListMethod = new ArrayList<>();
        for(Field field : aClass.getDeclaredFields()){
            if(field.isAnnotationPresent(ApiField.class)){
                subListField.add(field);
            }
        }
        //subListField.sort((o1, o2) -> o1.getAnnotation(ApiField.class).index() - o2.getAnnotation(ApiField.class).index());
        listField.addAll(subListField);

        for(Method method : aClass.getDeclaredMethods()){
            if(method.isAnnotationPresent(ApiField.class)){
                subListMethod.add(method);
            }
        }

        //subListMethod.sort((o1, o2) -> o1.getAnnotation(ApiField.class).index() - o2.getAnnotation(ApiField.class).index());
        listMethod.addAll(subListMethod);
    }

    public static String getApiName(Class<?> aClass){
        return aClass.getName();
    }

    public static List<Field> getAllApiFields(Class<?> aClass){
        return fields.get(getApiName(aClass));
    }

    public static List<Method> getAllApiMethods(Class<?> aClass){
        return methods.get(getApiName(aClass));
    }

    public static short getCmd(Class<?> aClass){
        return aClass.getAnnotation(ApiEntity.class).cmd();
    }
}
