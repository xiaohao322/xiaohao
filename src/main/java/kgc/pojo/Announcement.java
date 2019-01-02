package kgc.pojo;


import java.sql.Date;

public class Announcement {

  private long id;
  private String aTitle;
  private String aText;
  private java.sql.Date aDate;

  public Announcement(long id, String aTitle, String aText, Date aDate) {
    this.id = id;
    this.aTitle = aTitle;
    this.aText = aText;
    this.aDate = aDate;
  }

  public Announcement() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }


  public String getATitle() {
    return aTitle;
  }

  public void setATitle(String aTitle) {
    this.aTitle = aTitle;
  }


  public String getAText() {
    return aText;
  }

  public void setAText(String aText) {
    this.aText = aText;
  }


  public java.sql.Date getADate() {
    return aDate;
  }

  public void setADate(java.sql.Date aDate) {
    this.aDate = aDate;
  }

}
