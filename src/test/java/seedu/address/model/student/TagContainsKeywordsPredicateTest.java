package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class TagContainsKeywordsPredicateTest {
    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("friends");
        List<String> secondPredicateKeywordList = Arrays.asList("friends", "colleagues");

        TagContainsKeywordsPredicate firstPredicate = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        TagContainsKeywordsPredicate secondPredicate = new TagContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TagContainsKeywordsPredicate firstPredicateCopy = new TagContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different student -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_tagContainsKeywords_returnsTrue() {
        // One keyword
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.singletonList("friends"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Multiple keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("friend", "fri"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Only one matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("friends", "colleagues"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Mixed-case keywords
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("FrIenDS"));
        assertTrue(predicate.test(new PersonBuilder().withTags("friends").build()));
    }

    @Test
    public void test_tagDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        TagContainsKeywordsPredicate predicate = new TagContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Non-matching keyword
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("colleagues"));
        assertFalse(predicate.test(new PersonBuilder().withTags("friends").build()));

        // Keywords match tag, but does not match group
        predicate = new TagContainsKeywordsPredicate(Arrays.asList("John"));
        assertFalse(predicate.test(new PersonBuilder().withTags("friends").withName("John").build()));
    }
}

