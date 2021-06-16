package org.ow2.frascati.project.calcula-pi.annotated;

import org.osoa.sca.annotations.Init;
import org.osoa.sca.annotations.Reference;

public class ClientImpl
  implements Runnable
{

  private Client client;

  @Reference
  public final void setPrintService(Client client)
  {
    this.client = client;
  }

  public ClientImpl()
  {
    System.out.println("CLIENT created");
  }

  @Init
  public final void init()
  {
    System.out.println("CLIENT initialized");
  }

  public final void run()
  {
    System.out.println("Call the service...");
    s.print("hello world");
  }
}
