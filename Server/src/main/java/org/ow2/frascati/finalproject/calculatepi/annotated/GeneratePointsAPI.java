package main.java.org.ow2.frascati.finalproject.calculatepi.annotated;

import org.osoa.sca.annotations.Service;

/**
 * A basic service used to print messages. 
 */
@Service
public interface GeneratePointsAPI
{
	long generatePts(long ptsTotal, long seed);
}
