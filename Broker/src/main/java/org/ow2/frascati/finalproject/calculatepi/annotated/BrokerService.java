package main.java.org.ow2.frascati.finalproject.calculatepi.annotated;
import org.osoa.sca.annotations.Service;


@Service
public interface BrokerService{
    public void attachClient(String clienturi);
    public void detachClient(String clienturi);
    public void attachServer(String serveruri);
    public void detachServer(String serveruri);
	}