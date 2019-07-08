public class PublicPacket extends PacketBase {
    public PublicPacket() {
        super.register("aaaa",this.getClass());
    }

    @Override
    public void open() {

    }

    public void open(String id) {

    }

}
