package com.jotase.view;

import java.util.Random;

import aurelienribon.bodyeditor.BodyEditorLoader;
import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.jotase.model.Ball;

public class WorldRenderer {
    private World world;
    private static final float VIEWPORT_WIDTH = 10;
    private static final float BOTTLE_WIDTH = 8;
    private static final float BALL_RADIUS = 1f;
    private static final int MAX_BALLS = 1;

    private Body[] ballModels;
	private float ppuX; // pixels per unit on the X axis
	private float ppuY; // pixels per unit on the Y axis

    // Render

    private Texture ballTexture;
    
    private Sprite ballSprites;
    private Texture whiteTexture;
    private Sprite ballSprites2;
    
    private Ball ball;

    // Render general
    private SpriteBatch batch;
    private BitmapFont font;
    private OrthographicCamera camera;

    // Misc
    private final TweenManager tweenManager = new TweenManager();
    private final Random rand = new Random();
	public WorldRenderer() {
		super();
		 world = new World(new Vector2(0, -10), true);
		 ball = new Ball();
         batch = new SpriteBatch();
         font = new BitmapFont();
         font.setColor(Color.BLACK);

         float w = Gdx.graphics.getWidth();
         float h = Gdx.graphics.getHeight();
 		ppuX = (float) w;
 		ppuY = (float) h;
         camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_WIDTH*h/w);
         camera.position.set(0, camera.viewportHeight/2, 0);
         camera.update();
         createBalls();
         createSprites();
         render();
         restart();
	}
	private void createBalls()
	{
		BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("resource/test.json"));
        ballModels = new Body[MAX_BALLS];
        for (int i=0; i<MAX_BALLS; i++) {
                ballModels[i] = world.createBody(ball.getBallBodyDef());
                ballModels[i].createFixture(ball.getFd());
        }
        ball.getShape().dispose();
	}
    private void createSprites() {


        ballTexture = new Texture(Gdx.files.internal("resource/gfx/ball.png"));
        ballTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
        ballSprites2 = new Sprite(ballTexture);
        ballSprites2.setSize(BALL_RADIUS*3, BALL_RADIUS*3);
        ballSprites2.setOrigin(4,1);
        
                ballSprites = new Sprite(ballTexture);
                ballSprites.setSize(BALL_RADIUS*2, BALL_RADIUS*2);
                ballSprites.setOrigin(BALL_RADIUS, BALL_RADIUS);
            
}
	public void render()
	{
	    float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();

        // Update
        tweenManager.update(1/60f);
        world.step(1/60f, 10, 10);

        

        // Render
        GL10 gl = Gdx.gl10;
        gl.glClearColor(1, 1, 1, 1);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        drawBall();
        ballSprites.draw(batch);
        //ballSprites2.draw(batch);
    
        batch.end();
        
        batch.getProjectionMatrix().setToOrtho2D(0, 0, w, h);
        batch.begin();
        
        batch.end();
	}
	private void drawBall()
	{
		batch.draw(ballTexture, ball.getPosition().x * ppuX,
				ball.getPosition().y * ppuY,  ppuX,
						 ppuY);
	
	}
    public void restart() {
    	System.out.print("asdasdasd");
        Vector2 vec = new Vector2();

        for (int i=0; i<MAX_BALLS; i++) {
                float tx = rand.nextFloat() * 1.0f - 0.5f;
                float ty = camera.position.y + camera.viewportHeight/2 + BALL_RADIUS;
                float angle = rand.nextFloat() * MathUtils.PI * 2;

                ballModels[i].setActive(false);
                ballModels[i].setLinearVelocity(vec.set(0, 0));
                ballModels[i].setAngularVelocity(0);
                ballModels[i].setTransform(vec.set(tx, ty), angle);
        }

        tweenManager.killAll();

        Tween.call(new TweenCallback() {
                private int idx = 0;
                @Override 
                public void onEvent(int type, BaseTween<?> source) {
                        if (idx < ballModels.length) {
                                ballModels[idx].setAwake(true);
                                ballModels[idx].setActive(true);
                                idx += 1;
                        }
                }
			
        }).repeat(-1, 0.1f).start(tweenManager);
}
    
    
}

