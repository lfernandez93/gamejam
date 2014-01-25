package com.jotase.controller;

import java.util.HashMap;
import java.util.Map;

import com.jotase.model.Ball;



public class BallController {
	private static final long LONG_JUMP_PRESS = 150l;
	private static final float ACCELERATION = 3f;
	private static final float GRAVITY = -25f;
	private static final float MAX_JUMP_SPEED = 4f;
	private static final float DAMP = 0.3f;
	private static final float MAX_VEL = 2f;
	private static final float WIDTH = 10f;
	private boolean upPressing,downPressing,leftPressing,rightPressing;
	private Ball ball = new Ball();
	
	
	public boolean isUpPressing() {
		return upPressing;
	}

	public void setUpPressing(boolean upPressing) {
		this.upPressing = upPressing;
	}

	public boolean isDownPressing() {
		return downPressing;
	}

	public void setDownPressing(boolean downPressing) {
		this.downPressing = downPressing;
	}

	public boolean isLeftPressing() {
		return leftPressing;
	}

	public void setLeftPressing(boolean leftPressing) {
		this.leftPressing = leftPressing;
	}

	public boolean isRightPressing() {
		return rightPressing;
	}

	public void setRightPressing(boolean rightPressing) {
		this.rightPressing = rightPressing;
	}

	public static Map<Keys, Boolean> getKeys() {
		return keys;
	}

	public static void setKeys(Map<Keys, Boolean> keys) {
		BallController.keys = keys;
	}
	enum Keys {
		LEFT, RIGHT, UP, DOWN
	}
	public static long getLongJumpPress() {
		return LONG_JUMP_PRESS;
	}

	public static float getAcceleration() {
		return ACCELERATION;
	}

	public static float getGravity() {
		return GRAVITY;
	}

	public static float getMaxJumpSpeed() {
		return MAX_JUMP_SPEED;
	}

	public static float getDamp() {
		return DAMP;
	}

	public static float getMaxVel() {
		return MAX_VEL;
	}

	public static float getWidth() {
		return WIDTH;
	}
	static Map<Keys, Boolean> keys = new HashMap<BallController.Keys, Boolean>();
	static {
		keys.put(Keys.DOWN, false);
		keys.put(Keys.UP, false);
		keys.put(Keys.LEFT, false);
		keys.put(Keys.RIGHT, false);
	}
	public void leftPressed() {
		keys.get(keys.put(Keys.LEFT, true));
		leftPressing = true;
	}

	public void rightPressed() {
		keys.get(keys.put(Keys.RIGHT, true));
		rightPressing = true;
	}

	public void jumpPressed() {
		keys.get(keys.put(Keys.UP, true));
		upPressing = true;
	}

	public void firePressed() {
		keys.get(keys.put(Keys.DOWN, false));
		downPressing=true;
	}

	public void leftReleased() {
		keys.get(keys.put(Keys.LEFT, false));
		leftPressing = false;
	}

	public void rightReleased() {
		keys.get(keys.put(Keys.RIGHT, false));
		rightPressing = false;
	}

	public void jumpReleased() {
		keys.get(keys.put(Keys.UP, false));
		upPressing = false;

	}

	public void fireReleased() {
		keys.get(keys.put(Keys.DOWN, false));
		downPressing = false;

	}
	public void update(float delta) {
		// TODO Auto-generated method stub
		
	}
	/*private void setPosition() {
		if (ball.getPosition().y < 0) {
			ball.getPosition().y = 0f;
			ball.setPosition(ball.getPosition());
			if (ball.states.equals(State.JUMPING)) {
				ball.setState(State.IDLE);
			}
		}
		if (ball.getPosition().x < 0) {
			ball.getPosition().x = 0;
			ball.setPosition(ball.getPosition());
			if (!ball.getState().equals(State.JUMPING)) {
				ball.setState(State.IDLE);
			}
		}
		if (ball.getPosition().x > WIDTH - ball.getBounds().width) {
			ball.getPosition().x = WIDTH - ball.getBounds().width;
			ball.setPosition(ball.getPosition());
			if (!ball.getState().equals(State.JUMPING)) {
				ball.setState(State.IDLE);
			}
		}

	}*/

	private void setAcceleration(float delta) {
		float vel;
		if (upPressing) {
			vel = MAX_VEL / 2;
		} else
			vel = MAX_VEL;
		ball.getAcceleration().y = GRAVITY;
		ball.getAcceleration().mul(delta);
		ball.getVelocity().add(ball.getAcceleration().x,
				ball.getAcceleration().y);
		
		if (ball.getAcceleration().x == 0)
			ball.getVelocity().x *= DAMP;
		if (ball.getAcceleration().x > vel) {
			ball.getAcceleration().x = vel;
		}
		if (ball.getAcceleration().y < -vel)
			ball.getAcceleration().y = -vel;
	}

}
