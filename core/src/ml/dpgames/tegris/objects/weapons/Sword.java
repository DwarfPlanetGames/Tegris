package ml.dpgames.tegris.objects.weapons;

import ml.dpgames.tegris.TextureRender;
import ml.dpgames.tegris.TextureRender.Draw;
import ml.dpgames.tegris.objects.Player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class Sword implements Weapon {

	public static final int armLength = 7;
	
	public static final Texture streak = new Texture(
			"weapons/swords/sword_streak.png");
	public Texture tex;
	public float range;
	public float damage;
	public float swingTime;
	public boolean hasStreak;

	public boolean isSwinging = false;
	public float swingDelta = 0;

	public Sword(float range, float damage, float swingTime, Texture image,
			boolean hasStreak) {
		this.range = range;
		this.damage = damage;
		this.swingTime = swingTime;
		tex = image;
		this.hasStreak = hasStreak;
	}

	@Override
	public void update(float delta) {
		if (isSwinging) {
			swingDelta += (90 / (swingTime / 2)) * delta;
			if (swingDelta > 90 + 45) {
				swingDelta = -90;
				isSwinging = false;
			}
		}
	}

	@Override
	public void render(SpriteBatch batch, final float x, final float y, final float direction) {
		if (isSwinging) {
			TextureRender.addTex(new Draw() {
				public void render(SpriteBatch batch) {
					batch.draw(new TextureRegion(tex), x + (float)Math.cos(Math.toRadians(swingDelta + direction)) * armLength, y + (float)Math.sin(Math.toRadians(swingDelta + direction)) * armLength, tex.getWidth(),
							tex.getHeight() / 2.0f, tex.getWidth(),
							tex.getHeight(), 1.0f / 4.0f, 1.0f / 4.0f,
							swingDelta + 90 + direction);
				}
			}, (int) Player.Y);
		} else {
			TextureRender.addTex(new Draw() {
				public void render(SpriteBatch batch) {
					batch.draw(new TextureRegion(tex), x, y, tex.getWidth(),
							tex.getHeight() / 2.0f, tex.getWidth(),
							tex.getHeight(), 1.0f / 4.0f, 1.0f / 4.0f,
							(float) (270));
				}
			}, (int) Player.Y);
		}
	}

	@Override
	public void activate() {
		isSwinging = true;
	}

}
