package org.ow2.frascati.project.calcula-pi.annotated;

import org.osoa.sca.annotations.Service;

@Service
public interface Client
{
    void print(String msg);
}
