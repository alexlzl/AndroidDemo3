// IImoocAidl.aidl
package fingertip.creditease.com.aidlc;

// Declare any non-default types here with import statements
import fingertip.creditease.com.aidlc.Person;
interface IImoocAidl {
  int add(int num1, int num2);
  List<Person> addPerson(in Person person);

}
