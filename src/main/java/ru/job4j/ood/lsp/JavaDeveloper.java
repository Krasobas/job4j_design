package ru.job4j.ood.lsp;

public class JavaDeveloper extends Developer {

    public JavaDeveloper() {
        super();
        language = "java";
    }

    @Override
    public void code(int storyPoint) {
        /**
         * precondition case
         */
        super.code(storyPoint * 3);
    }

    @Override
    public void leadTeam() {
        /**
         * invariant case
         */
        if (experience > 10) {
            super.leadTeam();
        }
    }

    @Override
    public boolean test(String code) {
        /**
         * post condition case
         */
        return !super.test(code);
    }
}
