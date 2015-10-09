package ml.dpgames.tegris.objects.weapons;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Weapon {
	
	public void activate();
	
	public void update(float delta);
	
	public void render(SpriteBatch batch, float x, float y, float direction);
	
}
