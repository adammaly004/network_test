package cz.cvut.fel.malyada1.squareland.model;

import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import cz.cvut.fel.malyada1.squareland.utils.Collisions;

import static cz.cvut.fel.malyada1.squareland.utils.Constants.*;


public class Player extends Rectangle {

    Collisions collisions;

    int speed;
    int lives;
    double dx, dy;
    int timer = 0;
    boolean alive;
    int bulletsCount;
    String name;
    List<KeyCode> keys;


    private final List<Bullet> bullets = new ArrayList<>();

    public Player(double x, double y, int width, int height, int speed, String name, Collisions collisions) {
        super(x, y, width, height);
        setFill(Color.RED);
        this.speed = speed;
        this.name = name;
        this.collisions = collisions;
        lives = 3;
        bulletsCount = 3;
        dx = 0;
        dy = 0;
        alive = true;
        if (this.name.equals("player1")) {
            keys = PLAYER1_KEYS;
        } else {
           keys = PLAYER2_KEYS;
        }
    }

    private void moveLeft() {
        setX(getX() - this.speed);
        dx = -1;
        dy = 0;
    }

    private void moveRight() {
        setX(getX() + this.speed);
        dx = 1;
        dy = 0;
    }

    private void moveUp() {
        setY(getY() - this.speed);
        dx = 0;
        dy = -1;
    }

    private void moveDown() {
        setY(getY() + this.speed);
        dx = 0;
        dy = 1;
    }

    public void collisionWithBorder(double minX, double minY, double maxX, double maxY) {
        if (getX() < minX) {
            setX(minX);
        }
        if (getX() + getWidth() > maxX) {
            setX(maxX - getWidth());
        }
        if (getY() < minY) {
            setY(minY);
        }
        if (getY() + getHeight() > maxY) {
            setY(maxY - getHeight());
        }
    }

    public void keyPressed(Set<KeyCode> codes) {
        if(timer > 8 && alive) {
            if (codes.contains(keys.get(0))) {
                moveLeft();
                timer = 0;
            } else if (codes.contains(keys.get(1))) {
                moveRight();
                timer = 0;
            } else if (codes.contains(keys.get(2))) {
                moveUp();
                timer = 0;
            } else if (codes.contains(keys.get(3))) {
                moveDown();
                timer = 0;
            }
            if (codes.contains(keys.get(4))) {
                if (bulletsCount > 0) {
                    bulletsCount--;
                    fire();
                    timer = 0;
                }
            }
        }
        timer++;
    }

    private void fire() {
        Bullet bullet = new Bullet(getX() + getWidth() / 2, getY() + getHeight() / 2, dx, dy, 5, 1);
        bullets.add(bullet);
    }

    public void bulletCollision(List<Bullet> bullets) {
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()) {
            Bullet bullet = iterator.next();
            if (this.getBoundsInParent().intersects(bullet.getBoundsInParent())) {
                lives--;
                iterator.remove();
                if (lives == 0) {
                    alive = false;
                    setFill(Color.BLACK);
                }
            }
        }
    }

    public void update(Set<KeyCode> keyPressed) {
        double oldX = getX();
        double oldY = getY();

        this.keyPressed(keyPressed);
        this.collisionWithBorder(0, 0, WIDTH, HEIGHT);

        if (collisions.isCollidingWithTile(getX(), getY(), "player")) {
            setX(oldX);
            setY(oldY);
        }
        updateBullets();
    }

    private void updateBullets(){
        List<Bullet> bulletsToRemove = new ArrayList<>();
        for (Bullet bullet : bullets) {
            bullet.move();
            if (collisions.isCollidingWithTile(bullet.getCenterX(), bullet.getCenterY(), "bullet") || bullet.getCenterY() < 0 || bullet.getCenterY() > HEIGHT || bullet.getCenterX() < 0 || bullet.getCenterX() > WIDTH) {
                bulletsToRemove.add(bullet);
            }
        }
        bullets.removeAll(bulletsToRemove);
    }

    public List<Bullet> getBullets() {
        return bullets;
    }

    public void itemCollision(List<Rectangle> items) {
        Iterator<Rectangle> iterator = items.iterator();
        while (iterator.hasNext()) {
            Rectangle item = iterator.next();
            if (this.getBoundsInParent().intersects(item.getBoundsInParent())) {
                if (item instanceof AmmunitionPack) {
                    bulletsCount += ((AmmunitionPack) item).getAddedAmmo();
                } else if (item instanceof Heal) {
                    lives += ((Heal) item).getAddedLives();
                }
                iterator.remove();
            }
        }
    }

    public void reset() {
        setX(0);
        setY(0);
        dx = 0;
        dy = 0;
        bullets.clear();
        lives = 3;
        bulletsCount = 3;
        alive = true;
        setFill(Color.RED);
    }
}