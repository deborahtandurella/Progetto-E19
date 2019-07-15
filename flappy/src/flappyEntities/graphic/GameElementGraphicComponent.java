package flappyEntities.graphic;

import flappyEntities.logic.GameElementLogicComponent;
import graphics.Canvas;

/**
 *  Componente grafica per elementi di gioco per i quali è necessario che la componente grafica
 *  conosca quella logica
 */
public abstract class GameElementGraphicComponent implements GraphicComponent {
    private GameElementLogicComponent logicComponent;

    private Canvas canvas;
    @Override
    abstract public void render();
    public GameElementGraphicComponent(Canvas canvas) {
        this.canvas=canvas;
    }

    public void setLogicComponent(GameElementLogicComponent logicComponent) {
        this.logicComponent = logicComponent;
    }

    public GameElementLogicComponent getLogicComponent() {
        return logicComponent;
    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }
}
