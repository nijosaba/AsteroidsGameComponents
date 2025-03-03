package dk.sdu.cbse.player;


import dk.sdu.cbse.common.data.Entities;

public class Player extends Entities {

    private float maxSpeed;
    private float acceleration;
    private float deceleration;
    private int life;
    private boolean isHit;

    public Player() {

    }

    public Player(float maxSpeed, float acceleration, float deceleration, int life, boolean isHit) {
        this.maxSpeed = maxSpeed;
        this.acceleration = acceleration;
        this.deceleration = deceleration;
        this.life = life;
        this.isHit = isHit;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public float getDeceleration() {
        return deceleration;
    }

    public void setDeceleration(float deceleration) {
        this.deceleration = deceleration;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public boolean isHit() {
        return isHit;
    }

    public void setHit(boolean hit) {
        isHit = hit;
    }
}
