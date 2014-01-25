package com.jotase.model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Ball {
	private BodyDef ballBodyDef;
	final float density = 1;
	final float friction = 0.5f;
	final float restitution = 0.5f;
	
	private CircleShape shape;
	private FixtureDef fd;
	private Vector2 position = new Vector2();
	private Vector2 acceleration = new Vector2();
	private Vector2 velocity = new Vector2();
	public enum states {
		LEFT, RIGHT, UP, DOWN
	}
	public Vector2 getAcceleration() {
		return acceleration;
	}


	public void setAcceleration(Vector2 acceleration) {
		this.acceleration = acceleration;
	}


	public Vector2 getVelocity() {
		return velocity;
	}


	public void setVelocity(Vector2 velocity) {
		this.velocity = velocity;
	}


	public BodyDef getBallBodyDef() {
		return ballBodyDef;
	}


	public void setBallBodyDef(BodyDef ballBodyDef) {
		this.ballBodyDef = ballBodyDef;
	}


	public CircleShape getShape() {
		return shape;
	}


	public void setShape(CircleShape shape) {
		this.shape = shape;
	}


	public FixtureDef getFd() {
		return fd;
	}


	public void setFd(FixtureDef fd) {
		this.fd = fd;
	}


	public Vector2 getPosition() {
		return position;
	}


	public void setPosition(Vector2 position) {
		this.position = position;
	}


	public float getDensity() {
		return density;
	}


	public float getFriction() {
		return friction;
	}


	public float getRestitution() {
		return restitution;
	}


	public Ball() {
		super();
		this.ballBodyDef = new BodyDef();
		this.shape = new CircleShape();
		this.fd = new FixtureDef();
		this.position = new Vector2(5,5);
		setSettings();
	}


	private void setSettings() {
        fd.density = 1;
        fd.friction = 0.5f;
        fd.restitution = 0.5f;
        fd.shape = shape;
		
	}

}
