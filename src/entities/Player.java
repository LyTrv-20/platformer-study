package entities;

import static utils.Constants.PlayerConstants.*;
import static utils.HelpMethods.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utils.HelpMethods;
import utils.LoadSave;

public class Player extends Entity {
	private BufferedImage[][] animations;
	private int aniTick, aniIndex, aniSpeed = 25;
	private int playerAction = IDLE;
	private boolean moving = false, attacking = false;
	private boolean left, up, right, down, jump;
	private float playerSpeed = 1.0f * Game.SCALE;
	private int[][] lvlData;
	private float xDrawOffset = 20 * Game.SCALE;
	private float yDrawOffset = 3 * Game.SCALE
	 * Game.SCALE;

	// Jumping / Gravity
	private float airSpeed = 0f;
	private float gravity = 0.04f * Game.SCALE;
	private float jumpSpeed = -2.25f * Game.SCALE;
	private float fallSpeedAfterCollision = 0.5f * Game.SCALE;
	private boolean inAir = false;

	public Player(float x, float y, int width, int height) {
		super(x, y, width, height);
		loadAnimations();
		initHitbox(x, y, (int)(26 * Game.SCALE), (int)(30.0f * Game.SCALE));

	}

	public void update() {
		updatePos();
		updateAnimationTick();
		setAnimation();
	}

	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex], (int) (hitbox.x - xDrawOffset), (int) (hitbox.y - yDrawOffset), width, height, null);
		// drawHitbox(g);
	}

	private void updateAnimationTick() {
		aniTick++;
		if (aniTick >= aniSpeed) {
			aniTick = 0;
			aniIndex++;
			if (aniIndex >= GetAnimAmount(playerAction)) {
				aniIndex = 0;
				attacking = false;
			}

		}

	}

	private void setAnimation() {
		int startAni = playerAction;

		if (moving)
			playerAction = RUN;
		else
			playerAction = IDLE;

		if (inAir) {
			if (airSpeed < 0)
				playerAction = JUMP;
			else
				playerAction = FALL;
		}

		if (attacking)
			playerAction = ATK1;

		if (startAni != playerAction)
			resetAniTick();
	}

	private void resetAniTick() {
		aniTick = 0;
		aniIndex = 0;
	}

	private void updatePos() {
		moving = false;

		if (jump)
			jump();
		if (!left && !right && !inAir)
			return;

		float xSpeed = 0;

		if (left)
			xSpeed -= playerSpeed;
		if (right)
			xSpeed += playerSpeed;

		if (!inAir)
			if (!ISEntityOnFloor(hitbox, lvlData))
				inAir = true;

		if (inAir) {
			if (canMoveHere(hitbox.x, hitbox.y + airSpeed, hitbox.width, hitbox.height, lvlData)) {
				hitbox.y += airSpeed;
				airSpeed += gravity;
				updateXPos(xSpeed);
			} else {
				// hitbox.y = GetEntityYPosUnderRoofOrAboveFloor(hitbox, airSpeed);
				if (airSpeed > 0)
					resetInAir();
				else
					airSpeed = fallSpeedAfterCollision;
				updateXPos(xSpeed);
			}

		} else
			updateXPos(xSpeed);
		moving = true;
	}

	private void jump() {
		if (inAir)
			return;
		inAir = true;
		airSpeed = jumpSpeed;

	}

	private void resetInAir() {
		inAir = false;
		airSpeed = 0;

	}

	private void updateXPos(float xSpeed) {
		if (canMoveHere(hitbox.x + xSpeed, hitbox.y, hitbox.width, hitbox.height, lvlData)) {
			hitbox.x += xSpeed;
		} else {
			hitbox.x = GetEntityPosNextToWall(hitbox, xSpeed);
		}
	}

	private void loadAnimations() {

		BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);

		animations = new BufferedImage[12][6];
		for (int j = 0; j < animations.length; j++)
			for (int i = 0; i < animations[j].length; i++){
				animations[j][i] = img.getSubimage(i * 66, j * 44, 64, 40);
			}
		
	}

	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
		if (!HelpMethods.ISEntityOnFloor(hitbox, lvlData))
			inAir = true;
	}

	public void resetBool() {
		left = false;
		right = false;
		up = false;
		down = false;
	}

	public void setAttack(boolean attacking) {
		this.attacking = attacking;
	}

	public boolean isLeft() {
		return left;
	}

	public void setL(boolean left) {
		this.left = left;
	}

	// public boolean isUp() {
	// 	return up;
	// }

	// public void setU(boolean up) {
	// 	this.up = up;
	// }

	public boolean isRight() {
		return right;
	}

	public void setR(boolean right) {
		this.right = right;
	}

	// public boolean isDown() {
	// 	return down;
	// }

	// public void setDown(boolean down) {
	// 	this.down = down;
	// }

	public void setJump(boolean jump) {
		this.jump = jump;
	}

}