package flappyEntities.logic;

/**
 *  Interfaccia per componenti logiche che dispongono di una versione serializzabile
 */
public interface HasSerializableVersion extends LogicComponent {
    /**
     * @return versione serializzabile della componente logica
     */
    SerializableEntity getSerializableVersion();
}
