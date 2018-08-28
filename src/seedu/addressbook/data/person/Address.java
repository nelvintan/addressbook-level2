package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

import java.util.Objects;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    // public final String value;
    private boolean isPrivate;

    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        this.isPrivate = isPrivate;
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        String[] addressParts = trimmedAddress.split(", ");
        block = new Block(addressParts[0]);
        street = new Street(addressParts[1]);
            unit = new Unit(addressParts[2]);
        postalCode = new PostalCode(addressParts[3]);

        // this.value = trimmedAddress;
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return block.getValue() + ", "
                + street.getValue() + ", "
                + unit.getValue() + ", "
                + postalCode.getValue();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.block.getValue().equals(((Address) other).block.getValue())
                && this.street.getValue().equals(((Address) other).street.getValue())
                && this.unit.getValue().equals(((Address) other).unit.getValue())
                && this.postalCode.getValue().equals(((Address) other).postalCode.getValue())); // state check
    }

    @Override
    public int hashCode() {
        return Objects.hash(block, street, unit, postalCode);
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
