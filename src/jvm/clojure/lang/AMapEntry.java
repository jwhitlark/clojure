/**
 *   Copyright (c) Rich Hickey. All rights reserved.
 *   The use and distribution terms for this software are covered by the
 *   Common Public License 1.0 (http://opensource.org/licenses/cpl.php)
 *   which can be found in the file CPL.TXT at the root of this distribution.
 *   By using this software in any fashion, you are agreeing to be bound by
 * 	 the terms of this license.
 *   You must not remove this notice, or any other, from this software.
 **/

/* rich Mar 1, 2008 */

package clojure.lang;

import java.io.StringWriter;

public abstract class AMapEntry implements IMapEntry, IPersistentVector{

public boolean equals(Object obj){
	return APersistentVector.doEquals(this, obj);
}

public int hashCode(){
	//must match logic in APersistentVector
	return RT.hashCombine(RT.hashCombine(0,RT.hash(key())), RT.hash(val()));
}

public String toString(){
	StringWriter sw = new StringWriter();
	try
		{
		RT.print(this,sw);
		}
	catch(Exception e)
		{
		//checked exceptions stink!
		throw new RuntimeException(e);
		}
	return sw.toString();
}

public int length(){
	return 2;
}

public Object nth(int i){
	if(i == 0)
		return key();
	else if (i == 1)
		return val();
	else
		throw new IndexOutOfBoundsException();
}

private IPersistentVector asVector(){
	return PersistentVector.create(key(), val());
}

public IPersistentVector assocN(int i, Object val){
	return asVector().assocN(i, val);
}

public int count(){
	return 2;
}

public ISeq seq(){
	return asVector().seq();
}

public IPersistentVector cons(Object o){
	return asVector().cons(o);
}

public boolean containsKey(Object key){
	return asVector().containsKey(key);
}

public IMapEntry entryAt(Object key){
	return asVector().entryAt(key);
}

public Associative assoc(Object key, Object val){
	return asVector().assoc(key,val);
}

public Object valAt(Object key){
	return asVector().valAt(key);
}

public Object valAt(Object key, Object notFound){
	return asVector().valAt(key,notFound);
}

public Object peek(){
	return val();
}

public IPersistentStack pop(){
	return PersistentVector.create(key());
}

public ISeq rseq() throws Exception{
	return asVector().rseq();
}
}