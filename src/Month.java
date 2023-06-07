public enum Month {
    JAN("январе", 1),
    FEB("феврале", 2),
    MAR("марте", 3);

    private final String name;
    private final int order;
    Month(String name, int order) {
        this.name = name;
        this.order = order;
    }

    public String getName() {
        return name;
    }

    public int getOrder() {
        return order;
    }

    public static Month getByOrder(int oder){
        for (Month m : Month.values()) {
            if (oder == m.getOrder()) {
                return m;
            }
        }
        throw new IllegalArgumentException("Month with order = " + oder + " does not exists");
    }
}
