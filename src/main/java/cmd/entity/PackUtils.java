package cmd.entity;

import java.nio.ByteBuffer;
import java.util.List;
import bitzero.server.extensions.data.BaseMsg;

import domain.lobby.RoomStatusObject;
import domain.lobby.Abc;


public class PackUtils extends BaseMsg{
	private static PackUtils instance;

	private PackUtils() {
		super((short)0);
	}

	public static PackUtils getInstance(){
		if(instance == null){
			instance = new PackUtils();
		}
		return instance;
	}

    public void packRoomStatusObject(ByteBuffer bf, RoomStatusObject obj){
        bf.putInt(obj.id);
		putStr(bf, obj.name);
		putStr(bf, obj.gameStructureId);
		bf.put(obj.numberPlayer);
		bf.put((byte)(obj.proMode?1:0));
		bf.put((byte)(obj.lightMode?1:0));
		packAbc(bf, obj.fields);
		
    }

    public void packRoomStatusObject(ByteBuffer bf, List<RoomStatusObject> objs){
        bf.putShort((short) objs.size());
        for(RoomStatusObject obj: objs){
            packRoomStatusObject(bf, obj);
        }
    }
    public void packAbc(ByteBuffer bf, Abc obj){
        bf.putInt(obj.ddd);
		
    }

    public void packAbc(ByteBuffer bf, List<Abc> objs){
        bf.putShort((short) objs.size());
        for(Abc obj: objs){
            packAbc(bf, obj);
        }
    }

}
