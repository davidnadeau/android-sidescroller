package com.example.sidescroller.peripherals;

/**
 * Created by Owner on 14/11/13.
 */
public class Weapon {
    private String name;
    private int damage, rof; //ROF = rate of fire
    //image file
    private Projectile projectile;

    public Weapon() {}
    public Weapon(String name, int damage, int rof, Projectile projectile) {
    	this.name = name;
    	this.damage = damage;
    	this.rof = rof;
    	this.projectile = projectile;
    }

    public String getName() { return name; }
    public int getDamage() { return damage; }
    public int getRof() { return rof; }
    public Projectile getProjectile() { return projectile; }

    public void setName(String name) { this.name = name; }
    public void setDamage(int damage) { this.damage = damage; }
    public void setRof(int rof) { this.rof = rof; }
    public void setProjectile(Projectile projectile) { this.projectile = projectile; }
}