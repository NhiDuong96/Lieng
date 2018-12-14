package cmd.entity;

import java.nio.ByteBuffer;
import java.util.Collection;

import domain.gameplay.GameStatusObject;
import domain.gameplay.PlayerStatusObject;


public class PackUtils extends BasePackUtils{
	private static PackUtils instance;

	private PackUtils() {}

	public static PackUtils getInstance(){
		if(instance == null){
			instance = new PackUtils();
		}
		return instance;
	}

    public void packGameStatusObject(ByteBuffer bf, GameStatusObject obj){
       if(obj == null) {
            bf.put((byte)0);
            return;
        }
        bf.put((byte)1);
        packPlayerStatusObject(bf, obj.getPlayerList());
		putCollectionInt(bf, obj.getPositions());
		
    }

    public void packGameStatusObject(ByteBuffer bf, Collection<GameStatusObject> objs){
       if(objs == null || objs.size() == 0) {
            bf.put((byte)0);
            return;
        }
        bf.put((byte)1);
        bf.putShort((short) objs.size());
        for(GameStatusObject obj: objs){
            packGameStatusObject(bf, obj);
        }
    }
    public void packPlayerStatusObject(ByteBuffer bf, PlayerStatusObject obj){
       if(obj == null) {
            bf.put((byte)0);
            return;
        }
        bf.put((byte)1);
        putStr(bf, obj.getName());
		bf.put(obj.getGamePosition());
		
    }

    public void packPlayerStatusObject(ByteBuffer bf, Collection<PlayerStatusObject> objs){
       if(objs == null || objs.size() == 0) {
            bf.put((byte)0);
            return;
        }
        bf.put((byte)1);
        bf.putShort((short) objs.size());
        for(PlayerStatusObject obj: objs){
            packPlayerStatusObject(bf, obj);
        }
    }

}
