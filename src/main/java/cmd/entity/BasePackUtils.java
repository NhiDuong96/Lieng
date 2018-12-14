package cmd.entity;

import bitzero.server.extensions.data.BaseMsg;

import java.nio.ByteBuffer;
import java.util.Collection;

/**
 * Created by pc1 on 12/14/2018.
 */
public class BasePackUtils extends BaseMsg {
    public BasePackUtils() {
        super((short)-1);
    }

    public void putCollectionByte(ByteBuffer bf, Collection<Byte> collection){
        bf.putShort((short) collection.size());
        collection.forEach(bf::put);
    }

    public void putCollectionInt(ByteBuffer bf, Collection<Integer> collection){
        bf.putShort((short) collection.size());
        collection.forEach(bf::putInt);
    }

    public void putCollectionShort(ByteBuffer bf, Collection<Short> collection){
        bf.putShort((short) collection.size());
        collection.forEach(bf::putShort);
    }

    public void putCollectionLong(ByteBuffer bf, Collection<Long> collection){
        bf.putShort((short) collection.size());
        collection.forEach(bf::putLong);
    }

    public void putCollectionFloat(ByteBuffer bf, Collection<Float> collection){
        bf.putShort((short) collection.size());
        collection.forEach(bf::putFloat);
    }

    public void putCollectionDouble(ByteBuffer bf, Collection<Double> collection){
        bf.putShort((short) collection.size());
        collection.forEach(bf::putDouble);
    }

    public void putCollectionString(ByteBuffer bf, Collection<String> collection){
        bf.putShort((short) collection.size());
        for(String str: collection){
            putStr(bf, str);
        }
    }
}
