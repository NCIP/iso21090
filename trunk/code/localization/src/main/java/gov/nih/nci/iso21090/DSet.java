package gov.nih.nci.iso21090;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents the iso datatype DSET.
 * @author lpower
 * @param <T> the type
 */
public final class DSet<T extends Any> extends Coll<T> implements Serializable, Cloneable {

    private static final long serialVersionUID = 1L;
    private Set<T> item;

    /**
     * @return the item
     */
    public Set<T> getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Set<T> item) {
        this.item = item;
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

        if (!(obj instanceof DSet<?>)) {
            return false;
        }

        DSet<?> x = (DSet<?>) obj;

        return compareSets(this.getItem(), x.getItem());

    }

    private boolean compareSets(Set<?> currentSet, Set<?> compareSet) {
        if (currentSet == null && compareSet == null) {
            return true;
        } else if (currentSet != null && compareSet != null) {
            return compareSet.equals(currentSet);
        } else {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        if (this.getItem() != null) {
            return this.getItem().hashCode();
        }
        return super.hashCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings({ "PMD.ProperCloneImplementation", "unchecked" })
    public DSet<T> clone() {

        DSet<T> returnVal = new DSet<T>();
        if (this.getItem() != null) {
            returnVal.setItem(new HashSet<T>());

            try {
                for (T tem : this.getItem()) {
                    returnVal.getItem().add((T) tem.clone());
                }
            } catch (Exception e) {
                throw new IsoCloneException(e);
            }
        }

        return returnVal;
    }

}
