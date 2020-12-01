package Logica;



/**
 * Class BTKeyValue
 * @author tnguyen
 */
public class BTKeyValue<K extends Comparable, V>
{
    public K mKey;
    protected V mValue;

    public BTKeyValue(K key, V value) {
        mKey = key;
        mValue = value;
    }
}
