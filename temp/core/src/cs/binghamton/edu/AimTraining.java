package cs.binghamton.edu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.ScreenUtils;
import java.util.Random;


public class AimTraining extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;

	float x;
	float y;

	Rectangle target_rect; // target rectangle will the rectangle which covers my texture

	// to move my object to a random place
	Random rand_x;
	Random rand_y;




	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("crosshair.png");

		//target rectangle on x=500, y=500 as a start point, also the same height and width with my texture/objcets
		target_rect = new Rectangle(500,
				500,
				img.getWidth(), img.getHeight());

		//Creating random variables
		rand_x = new Random();
		rand_y = new Random();
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		batch.begin();

		// drawing my object on target_rect's position however making my object smaller by dividing height and width with 2
		batch.draw(img, target_rect.x, target_rect.y, target_rect.width/2, target_rect.height/2);


		// if user touches the screen
		if (Gdx.input.justTouched()){

			// creating new vector2 to store touched position's "x" and "y"
			Vector2 touched_pos = new Vector2(Gdx.input.getX(), Gdx.graphics.getHeight()-Gdx.input.getY());

			// creating a touched rectangle by using touched position
			Rectangle touched_rect = new Rectangle(touched_pos.x,touched_pos.y, 1,1);

			// if user touches the target object
			if(Intersector.overlaps(touched_rect, target_rect)){
				x = rand_x.nextInt(Gdx.graphics.getWidth()-img.getWidth());// upperbound of x, "-getWidth" objects width
				y = rand_y.nextInt(Gdx.graphics.getHeight()- img.getHeight()); // upperbound of y

				target_rect.setPosition(x,y); //changing the position of target rect which means my object
			}
		}

		batch.end();
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
