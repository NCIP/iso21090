package gov.nih.nci.iso21090;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents iso data type AD.
 * @author lpower
 */
public final class Ad extends Any implements Cloneable {

    private static final long serialVersionUID = 1L;
    // TODO Invariant must be applied - see COPPA ISO's from EA
    private List<Adxp> part;

    /**
     * @return the part
     */
    public List<Adxp> getPart() {
        return part;
    }

    /**
     * @param part the part to set
     */
    public void setPart(List<Adxp> part) {
        this.part = part;
    }

    
    /**
     * @param partInstance ADXP part to be added to the part collection
     */
    public void addPart(Adxp partInstance) {
        if (part == null) {
            part = new ArrayList<Adxp>();
        }
        if (partInstance != null) {
            part.add(partInstance);
        }
    }
    
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Ad)) {
            return false;
        }

        Ad x = (Ad) obj;

        return super.equals(obj) && compareLists(this.getPart(), x.getPart());

    }

    private boolean compareLists(List<Adxp> currentList, List<Adxp> compareList) {
        if (currentList == null && compareList == null) {
            return true;
        } else if (currentList != null && compareList != null) {
            return currentList.equals(compareList);
        } else {
            return false;
        }
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        if (this.getPart() != null) {
            return this.getPart().hashCode();
        }
        return super.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Ad clone() {

        Ad returnVal = (Ad) super.clone();
        if (this.getPart() != null) {
            returnVal.setPart(new ArrayList<Adxp>());

            try {
                for (Adxp tem : this.getPart()) {
                    returnVal.getPart().add(tem.clone());
                }
            } catch (Exception e) {
                throw new IsoCloneException(e);
            }
        }

        return returnVal;
    }
}
