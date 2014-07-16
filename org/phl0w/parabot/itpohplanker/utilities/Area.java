package org.phl0w.parabot.itpohplanker.utilities;

import org.rev317.min.api.wrappers.Tile;

import java.awt.*;

public class Area {
    private Polygon p;

    public Area(Tile... tiles) {
        this.p = new Polygon();
        for (int i = 0; i < tiles.length; i++) {
            this.p.addPoint(tiles[i].getX(), tiles[i].getY());
        }
    }

    public boolean contains(Tile tile) {
        return contains(tile.getX(), tile.getY());
    }

    public boolean contains(int x, int y) {
        boolean result = false;
        int i = 0;
        for (int j = this.p.npoints - 1; i < this.p.npoints; j = i++) {
            if ((this.p.ypoints[i] > y - 1 ? 1 : 0) != (this.p.ypoints[j] > y - 1 ? 1 : 0)) {
                if (x <= (this.p.xpoints[j] - this.p.xpoints[i]) * (y - this.p.ypoints[i]) / (this.p.ypoints[j] - this.p.ypoints[i]) + this.p.xpoints[i]) {
                    result = !result;
                }
            }
        }
        return result;
    }
}
