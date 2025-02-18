package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GroupBuilder;
import seedu.address.testutil.IdBuilder;
import seedu.address.testutil.PersonBuilder;

public class GroupTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Group(null));
    }

    @Test
    public void constructor_invalidGroupGroup_throwsIllegalArgumentException() {
        String invalidValue = "";
        assertThrows(IllegalArgumentException.class, () -> new Group(invalidValue));
    }

    @Test
    public void isValidGroup() {
        // null Group name
        assertThrows(NullPointerException.class, () -> Group.isValidGroup(null));

        // invalid Group name
        assertFalse(Group.isValidGroup("")); // empty string
        assertFalse(Group.isValidGroup(" ")); // spaces only

        // valid Group name
        assertTrue(Group.isValidGroup("T03C")); // upper case
        assertTrue(Group.isValidGroup("r05b")); // lower case
        assertTrue(Group.isValidGroup("r03A")); // mix of upper lower case
        assertTrue(Group.isValidGroup("r03 A")); // with spaces
        assertTrue(Group.isValidGroup("r")); // words only
        assertTrue(Group.isValidGroup("03")); // numbers only
    }

    @Test
    public void hasStudent_null_throwsNullPointerException() {
        Group group = new GroupBuilder().build();
        assertThrows(NullPointerException.class, () -> group.hasStudent(null));
    }

    @Test
    public void hasStudent_notIncluded() {
        ID id = new IdBuilder().build();
        Group group = new GroupBuilder().build();
        assertFalse(() -> group.hasStudent(id));
    }

    @Test
    public void hasStudent_included() {
        Student student = new PersonBuilder().build();
        Group group = new GroupBuilder().withStudents(student).build();
        assertTrue(() -> group.hasStudent(student.getId()));
    }
}
