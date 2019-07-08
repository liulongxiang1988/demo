import java.util.HashMap;
import java.util.Map;

public abstract class PacketBase implements PacketAction{
    private  final static Map<String,Class> map = new HashMap();

    public void register(String key, Class < ? extends PacketBase> clazz){
        map.put(key,clazz);
    }
}
