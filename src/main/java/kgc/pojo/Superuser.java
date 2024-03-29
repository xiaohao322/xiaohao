package kgc.pojo;


public class Superuser {

  private long id;
  private String userName;
  private String userPassword;
  private String userImage;
  private long userStatus;
  private String userId;
  private String userLoginName;

  public Superuser(long id, String userName, String userPassword, String userImage, long userStatus, String userId, String userLoginName) {
    this.id = id;
    this.userName = userName;
    this.userPassword = userPassword;
    this.userImage = userImage;
    this.userStatus = userStatus;
    this.userId = userId;
    this.userLoginName = userLoginName;
  }
  public Superuser(){};

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }


  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }


  public String getUserImage() {
    return userImage;
  }

  public void setUserImage(String userImage) {
    this.userImage = userImage;
  }


  public long getUserStatus() {
    return userStatus;
  }

  public void setUserStatus(long userStatus) {
    this.userStatus = userStatus;
  }


  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }


  public String getUserLoginName() {
    return userLoginName;
  }

  public void setUserLoginName(String userLoginName) {
    this.userLoginName = userLoginName;
  }

}
