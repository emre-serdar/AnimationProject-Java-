package cs.binghamton.edu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class AimTraining extends ApplicationAdapter {
	SpriteBatch batch;

	Texture img;

	float x;
	float y;

	Rectangle target_rect; // target rectangle will the rectangle which covers my texture

	// to move my object to a random place
	private Random rand_x;
	private Random rand_y;

	private Timer gameTimer;
	private int secondsLeft;
	private final int seconds = 25; //duration of the game
	private boolean gameOver = false;
	private int score;

	private BitmapFont font;



	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");

		//target rectangle on x=500, y=500 as a start point, also the same height and width with my texture/objcets
		target_rect = new Rectangle(500,
				500,
				img.getWidth(), img.getHeight());

		//Creating random variables
		rand_x = new Random();
		rand_y = new Random();

		gameTimer = new Timer();




		font = new BitmapFont();
		font.setColor(Color.WHITE);
		font.getData().setScale(5);

		startGame();
		startTimer();
	}

	@Override
	public void render () {

		if (secondsLeft <= 0 ){
			gameOver = true;

		}

		//clearing the background
		ScreenUtils.clear(1, 0, 0, 1);

		batch.begin();


		if (!gameOver) {
			batch.draw(img, target_rect.x, target_rect.y, target_rect.width/2, target_rect.height/2);


			// drawing my object on target_rect's position however making my object smaller by dividing height and width with 2
			font.draw(batch, "Seconds Left: " + secondsLeft, 150, Gdx.graphics.getHeight() - 150);
			font.draw(batch, "Your score: " + score, 150, 150);

			// if user touches the screen
			if (Gdx.input.justTouched()) {

				// creating new vector2 to store touched position's "x" and "y"
				Vector2 touched_pos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight() - Gdx.input.getY());

				// creating a touched rectangle by using touched position
				Rectangle touched_rect = new Rectangle(touched_pos.x, touched_pos.y, 1, 1);

				// if user touches the target object
				if (Intersector.overlaps(touched_rect, target_rect)) {
					x = rand_x.nextInt(Gdx.graphics.getWidth() - img.getWidth());// upperbound of x, "-getWidth" objects width
					y = rand_y.nextInt(Gdx.graphics.getHeight() - img.getHeight()); // upperbound of y
					target_rect.setPosition(x, y); //changing the position of target rect which means my object

					//increase score
					score++;
				}
			}
		}
		else {
			if(Gdx.input.justTouched())
				startGame();
			font.draw(batch, "Game Over! Your score was: " + score, 150, Gdx.graphics.getHeight() - 150);

		}
		batch.end();
	}

	private void startTimer(){
		secondsLeft = seconds;
		gameTimer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				if(!gameOver){
					secondsLeft--;
				}
			}
		},0,1000);
	}

	private void startGame(){
		score = 0;
		gameOver = false;
		secondsLeft = seconds;
		target_rect = new Rectangle(500,
				500,
				img.getWidth(), img.getHeight());
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
