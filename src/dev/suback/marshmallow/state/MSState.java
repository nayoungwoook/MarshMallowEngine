package dev.suback.marshmallow.state;

import dev.suback.marshmallow.MSMain;

public interface MSState {

	public void init();

	public void update();

	public void render();

	public static void setState(MSState state) {
		MSMain.renderObjects.clear();
		state.init();
		MSMain.state = state;
	}

}
