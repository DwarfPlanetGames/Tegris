package ml.dpgames.tegris.objects;

import ml.dpgames.tegris.GameObject;
import ml.dpgames.tegris.TextureRender;
import ml.dpgames.tegris.TextureRender.Draw;
import ml.dpgames.tegris.objects.weapons.Sword;
import ml.dpgames.tegris.objects.weapons.Weapon;
import ml.dpgames.tegris.screens.GameScreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Player extends GameObject {

	public static final Texture tex = new Texture("player.png");
	public static float WALK_SPEED = 80f;
	public Weapon weapon;
	public static float X = 0, Y = 0;
	public int direction = 0;

	public Player(float x, float y) {
		super(x, y, tex.getWidth() / 2, tex.getWidth() / 3);
		weapon = new Sword(0,0,0.2f,new Texture("weapons/swords/laser_sword.png"),false){};
	}

	@Override
	public void update(float delta) {
		if (Gdx.input.isKeyPressed(Keys.W) || Gdx.input.isKeyPressed(Keys.UP)) {
			y += WALK_SPEED * delta;
			direction = 90;
		}
		if (Gdx.input.isKeyPressed(Keys.S) || Gdx.input.isKeyPressed(Keys.DOWN)) {
			y -= WALK_SPEED * delta;
			direction = 270;
		}
		if (Gdx.input.isKeyPressed(Keys.D)
				|| Gdx.input.isKeyPressed(Keys.RIGHT)) {
			x += WALK_SPEED * delta;
			direction = 0;
		}
		if (Gdx.input.isKeyPressed(Keys.A) || Gdx.input.isKeyPressed(Keys.LEFT)) {
			x -= WALK_SPEED * delta;
			direction = 180;
		}
		// Detect collisions
		for (int x = getXOnGrid() - 2; x < getXOnGrid() + 2; x++) {
			for (int y = getYOnGrid() - 2; y < getYOnGrid() + 2; y++) {
				GameObject o = null;
				try {
					o = GameScreen.gridObjects[x][y];
				} catch (ArrayIndexOutOfBoundsException e) {
				}
				if (o != null) {
					if (o.getBounds() != null) {
						if (o.getBounds().overlaps(getBoundsTop())) {
							this.y = o.getBounds().y - height;
						}
						if (o.getBounds().overlaps(getBoundsBot())) {
							this.y = o.getBounds().y + o.getBounds().height;
						}
						if (o.getBounds().overlaps(getBoundsRit())) {
							this.x = o.getBounds().x - width;
						}
						if (o.getBounds().overlaps(getBoundsLft())) {
							this.x = o.getBounds().x + o.getBounds().width;
						}
					}
				}
			}
		}
		// Limit the player to the map
		if (x > GameScreen.mapWidth * 16)
			x = GameScreen.mapWidth * 16;
		if (y > GameScreen.mapWidth * 16)
			y = GameScreen.mapHeight * 16;
		if (x < 0)
			x = 0;
		if (y < 0)
			y = 0;
		// Set the camera location
		GameScreen.camera.position.x = x + tex.getWidth() / 4;
		GameScreen.camera.position.y = y + tex.getHeight() / 4;
		// Clamp the camera
		if (GameScreen.camera.position.x < GameScreen.camera.viewportWidth / 2)
			GameScreen.camera.position.x = GameScreen.camera.viewportWidth / 2;
		if (GameScreen.camera.position.y < GameScreen.camera.viewportHeight / 2)
			GameScreen.camera.position.y = GameScreen.camera.viewportHeight / 2;
		// Update the weapon
		X = x;
		Y = y;
		if (Gdx.input.isButtonPressed(Buttons.LEFT)) {
			weapon.activate();
		}
		weapon.update(delta);
	}

	public int getXOnGrid() {
		return Math.round(x / 16);
	}

	public int getYOnGrid() {
		return Math.round(y / 16);
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle(x + width / 4, y + height/2, width/2, height/2);
	}

	public Rectangle getBoundsBot() {
		return new Rectangle(x + width / 4, y, width/2, height/2);
	}

	public Rectangle getBoundsRit() {
		return new Rectangle(x + width/2, y + height / 4, width/2, height/2);
	}

	public Rectangle getBoundsLft() {
		return new Rectangle(x, y + height / 4, width/2, height/2);
	}

	@Override
	public void render(SpriteBatch batch) {
		TextureRender.addTex(new Draw() {
			public void render(SpriteBatch batch) {
				batch.draw(tex, x, y, tex.getWidth() / 2, tex.getHeight() / 2);
			}
		}, (int) y);
		weapon.render(batch,x-58,y+8,direction);
	}

}
