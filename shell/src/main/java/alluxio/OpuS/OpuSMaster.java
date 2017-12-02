package alluxio.OpuS;

import java.util.*;
import alluxio.OpuS.*;
import alluxio.cli.AlluxioShell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Created by yyuau on 12/7/2017.
 */
public class OpuSMaster extends TimerTask{

  private static final Logger LOG = LoggerFactory.getLogger(AlluxioShell.class);
  public static Map<Integer, User> userMap = new HashMap<Integer, User>();
  public static ArrayList<String> fileLibrary = new ArrayList<String>();
  public static int lastUserId = 0;


  public OpuSMaster() {
    LOG.info("OpuS started");
  }

  public static void addUser(Integer id, User user) {
    userMap.put(id, user);
  }

  public static void access(Integer userId, String filePath, boolean isHit){
    if (!userMap.containsKey(userId)) {
      System.out.println("Create user " + userId);
      User newUser = new User();
      newUser.id = userId;
      userMap.put(userId, newUser);
      printUserMap();
    }
    User thisUser = userMap.get(userId);
    thisUser.access(filePath, isHit);
    if(!fileLibrary.contains(filePath)){
      System.out.println("Add to library: " + filePath);
      fileLibrary.add(filePath);
      printFileLibrary();
    }


  }

  public static void printUserMap() {
    for (Integer userId: userMap.keySet()) {
      System.out.println(userId + " " + userMap.get(userId));
    }
  }

  public static void printFileLibrary() {
    for (String filePath: fileLibrary) {
      System.out.println(filePath);
    }
  }

  public static void logUserMap() {
    for (Integer userId: userMap.keySet()) {
      LOG.info(userId + " " + userMap.get(userId));
    }
  }

  public void run() {
    // Date now = new Date(); // initialize date
    // System.out.println("Time is :" + now); // Display current time
    LOG.info("OpusMaster number of users: "+ userMap.size());
    LOG.info("OpusMaster last-access user id: "+ lastUserId);
    logUserMap();
    // if (!lastMap.equals(Test.users)) {
    //t.printMap();
    //lastMap = new HashMap<>(Test.users);
    // }
  }
}
