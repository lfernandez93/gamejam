package com.wisecounsil.whoami.model;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

	
    public abstract  class BaseGameEntity {
 	
		 private Vector2 position;
		 public Vector2 getPosition() {
			return position;
		}

		public void setPosition(Vector2 position) {
			this.position = position;
		}

		private Vector2 dimension;
		 
		 public Vector2 getDimension() {
			return dimension;
		}

		public void setDimension(Vector2 dimension) {
			this.dimension = dimension;
		}

		public BaseGameEntity(){
			 position = new Vector2();
			 dimension = new Vector2();
		 }
		 
		 public abstract void update();
		 
		 public abstract void render(SpriteBatch batch);
		 
		 
		
	}

