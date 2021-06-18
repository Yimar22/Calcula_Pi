package main.java.org.ow2.frascati.finalproject.calculatepi.annotated;


@Service
public interface BringPointsAPI{
	  public BringPoints getPoints;
	  public int pointsCount();
	   public LinkedList<GeneratePointsAPI> getAvailablePoints();
	}