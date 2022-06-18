import processing.core.PApplet;
public class Sketch extends PApplet {


  float fltPlayerX = 200;
  float fltPlayerY = 350;
  float fltSnowSpeed = 5;
  int intLives = 3;
  int intRectX = 300;
  boolean blnClicking = false;

  float[] circleX = new float [25];
  float[] circleY = new float [25];
  boolean[] ballHideStatus = new boolean [25];


  
  public void settings() {
    // put your size call here
    size(400, 400);
  }

  public void setup() {
    for (int i = 0; i < circleY.length; i++){
      circleY[i] = random(height);
      circleX[i] = random(width);
      ballHideStatus[i] = false;
    }
  }

  public void draw() {

    if (intLives == 0){
      fill(255);
      rect(400, 400, 0, 0);
    }

    if (intLives > 0){
    //snowfall
    background(0);
    // loop draw circles based on array length 
    for (int i = 0; i < circleY.length; i++){
      if (ballHideStatus[i] == false){
        ellipse(circleX[i], circleY[i], 25, 25);
        circleY[i] += fltSnowSpeed;
      }

      if (blnClicking == true){
        if (dist(mouseX, mouseY, circleX[i], circleY[i]) <= 25 && ballHideStatus[i] == false){
        ballHideStatus[i] = true;
    }
      }
        
      

      // Edge Detection
      if (circleY[i] > height) {
        circleY[i] = 0;
      }
      
      if (fltPlayerY < 0) {
        fltPlayerY = 0;
      }
      
      if (fltPlayerY > height) {
        fltPlayerY = height;
      }
      
      if (fltPlayerX < 0) {
        fltPlayerX = 0;
      }

      if (fltPlayerX > width) {
        fltPlayerX = width;
      }

      // detects collision with snowballs and player, minus one life
      if (dist(fltPlayerX, fltPlayerY, circleX[i], circleY[i]) <= 37.4 && ballHideStatus[i] == false){
        intLives -= 1;
        ballHideStatus[i] = true;
      }
    }
  

    // Player controls
    fill(234);
    ellipse(fltPlayerX, fltPlayerY, 50, 50);


    for (int i = 0; i < intLives; i++){
      rect(300+30*i, 30, 20, 20);
    }
  }
  }
  

    /**
    * If key is pressed snow speed increases and if w,a,s,d keys are pressed the player is moved
    *
    * @param keyPressed
    * @return if up,down keys are pressed the snow speeds up, if w,a,s,d keys are pressed, the player x,y position moves
    * 
    */
  public void keyPressed() {
    if (keyCode == UP) {
      fltSnowSpeed = 1;
    } 
    if (keyCode == DOWN) {
      fltSnowSpeed = 10;
    }   
    if (key == 'w') {
      fltPlayerY -= 5;
    }   
    if (key == 's') {
      fltPlayerY += 5;
    }   
    if (key == 'a') {
      fltPlayerX -= 5;
    }   
    if (key == 'd') {
      fltPlayerX += 5;
    }   
  }  

    /**
    * If key is released snow speed turns back to normal
    *
    * @param keyReleased
    * @return if key is released fltSnowSpeedX and Y become 5
    * 
    */
  public void keyReleased() {
    if (keyCode == UP) {
      fltSnowSpeed = 5;
    }
    else if (keyCode == DOWN) {
      fltSnowSpeed = 5;
    }
  }

  
    /**
    * If mouse is pressed blnClicking becomes true and snowballs are deleted
    *
    * @param mousePressed
    * @return if mouse is pressed blnClicking = true
    * 
    */
  public void mousePressed() {
    blnClicking = true;
  }

    /**
    * If mouse is released blnClicking becomes false and snowballs aren't deleted
    *
    * @param mouseReleased
    * @return if mouse is released blnClicking = false
    * 
    */
  public void mouseReleased() {
    blnClicking = false;
  }
}