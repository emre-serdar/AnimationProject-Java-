package cs.binghamton.edu;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

public class AimTraining extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	float x = 200;
	float y = 100;

	float xSpeed = 2;
	float ySpeed = 1;
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		ScreenUtils.clear(1, 0, 0, 1);
		x+=xSpeed;
		y+=ySpeed;
		
		if ( x<0 || x > Gdx.graphics.getWidth()-100) {// since there is a width on my object
			xSpeed *= -1;
		}
		if ( y<0 || y > Gdx.graphics.getHeight() -100 ){
			ySpeed *= -1;
		}
		batch.begin();
		batch.draw(img, x, y);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
