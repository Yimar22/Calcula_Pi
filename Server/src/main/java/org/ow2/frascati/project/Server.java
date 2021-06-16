package org.ow2.frascati.project.calcula-pi.annotated;

import org.osoa.sca.annotations.Property;

public class ServerImpl
  implements Server
{
    @Property
    private String header = "->";

    private int count = 1;

    public Server()
    {
        System.out.println("SERVER created.");
    }
    

    public final void print(final String msg)
    {
        System.out.println("SERVER: begin printing...");
        for (int i = 0; i < count; ++i) {
            System.out.println(header + msg);
        }
        System.out.println("SERVER: print done.");
    }

}
