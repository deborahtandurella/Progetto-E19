package flappyEntities;

import flappyEntities.graphic.GraphicComponent;
import flappyEntities.logic.LogicComponent;

public abstract class Entity {
    private GraphicComponent graphicComponent;
    private LogicComponent logicComponent;
    private int ID;
    public Entity(LogicComponent logicComponent,GraphicComponent graphicComponent) {
        this.graphicComponent = graphicComponent;
        this.logicComponent = logicComponent;
    }

    public GraphicComponent getGraphicComponent() {
        return graphicComponent;
    }

    public void setGraphicComponent(GraphicComponent graphicComponent) {
        this.graphicComponent = graphicComponent;
    }

    public LogicComponent getLogicComponent() {
        return logicComponent;
    }

    public void setLogicComponent(LogicComponent logicComponent) {
        this.logicComponent = logicComponent;
    }
    public void update(double delta){
        if (logicComponent != null) {
            logicComponent.update(delta);
        }
    }
    public void render(){
        if (graphicComponent != null) {
            graphicComponent.render();
        }
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
}
