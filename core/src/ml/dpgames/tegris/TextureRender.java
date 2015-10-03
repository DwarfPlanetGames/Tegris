package ml.dpgames.tegris;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class TextureRender {

	public static Layer[] layers = new Layer[100000];
	public static LinkedList<Integer> usedLayers = new LinkedList<Integer>();

	public static void init() {
		for (int i = 0; i < layers.length; i++) {
			layers[i] = new Layer();
		}
	}

	public static void clear() {
		for (int i : usedLayers) {
			layers[i].draws.clear();
		}
		usedLayers.clear();
	}

	public static void render(SpriteBatch batch) {
		for (int i = 99999; i >= 0; i--) {
			if (usedLayers.contains(i))
				layers[i].render(batch);
		}
	}

	public static void addTex(Draw draw, int layer) {
		layer /= 3;
		layer += 50000;
		layers[layer].draws.add(draw);
		if (!usedLayers.contains(layer)) {
			usedLayers.add(layer);
		}
	}

	public static class Layer {
		public LinkedList<Draw> draws = new LinkedList<Draw>();

		public void render(SpriteBatch batch) {
			for (Draw i : draws) {
				i.render(batch);
			}
		}
	}

	public interface Draw {
		public void render(SpriteBatch batch);
	}

}
