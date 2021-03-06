package flappyEntities;

import flappyEntities.graphic.GraphicComponent;
import flappyEntities.logic.LogicComponent;

/**
 *  Entity rappresenta un'entità di gioco, può essere dotata di una parte gragica ed una logica
 */
public class Entity {
    private GraphicComponent graphicComponent;
    private LogicComponent logicComponent;
    private int ID; //ID univoco dell'entità
    public Entity(LogicComponent logicComponent,GraphicComponent graphicComponent) {
        this.graphicComponent = graphicComponent;
        this.logicComponent = logicComponent;
    }

    /**
     * @return la componente grafica dell'Entity
     */
    public GraphicComponent getGraphicComponent() {
        return graphicComponent;
    }

    public void setGraphicComponent(GraphicComponent graphicComponent) {
        this.graphicComponent = graphicComponent;
    }

    /**
     * @return la componente logica dell'Entity
     */
    public LogicComponent getLogicComponent() {
        return logicComponent;
    }

    public void setLogicComponent(LogicComponent logicComponent) {
        this.logicComponent = logicComponent;
    }

    /**
     * Update della parte logica, se presente
     * @param delta: intervallo di tempo
     */
    public void update(double delta){
        if (logicComponent != null) {
            logicComponent.update(delta);
        }
    }

    /**
     * Render della parte grafica
     */
    public void render(){
        if (graphicComponent != null) {
            graphicComponent.render();
        }
    }

    /**
     * @return l'ID univoco dell'Entity
     */
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
