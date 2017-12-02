package alluxio.OpuS;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yyuau on 11/7/2017.
 */
public class User {

  public Integer id;
  public Double effectiveHit;
  public Integer totalAccess;
  Integer factor = 1; // for taxing of VCG mechanism
  Map<String, Integer> accessMap = new HashMap<String, Integer>();

  public User() {
    effectiveHit = 0.0;
    totalAccess = 0;
  }

  public void access (String filePath, boolean isHit) {
    if (accessMap.containsKey(filePath)) {
      Integer count = accessMap.get(filePath);
      accessMap.put(filePath, count+1);
    }
    else
      accessMap.put(filePath, 1);
    totalAccess += 1;
    if (isHit) {
      System.out.println("User " + id + ": cache hit when accessing " + filePath);
      effectiveHit += factor;
    }
    printAccessMap();
  }

  public void printAccessMap() {
    System.out.print("Access Profile of User " + this.id);
    for (String key: accessMap.keySet()) {
      System.out.println(key + " " + accessMap.get(key));
    }
  }
}
