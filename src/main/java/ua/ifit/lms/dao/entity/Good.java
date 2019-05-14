package ua.ifit.lms.dao.entity;

public class Good {
    private Long idGood;
    private Long count_of_goods;
    private String good_name;
    private String picture_file_name;
    private Float price;
    private String description;

    public Good() {
    }

    public Good(Long idGood, Long count_of_goods, String good_name, String picture_file_name, Float price, String description) {
        this.idGood = idGood;
        this.count_of_goods = count_of_goods;
        this.good_name = good_name;
        this.picture_file_name = picture_file_name;
        this.price = price;
        this.description = description;
    }

    public Long getIdGood() {
        return idGood;
    }

    public void setIdGood(Long idGood) {
        this.idGood = idGood;
    }

    public Long getCount_of_goods() {
        return count_of_goods;
    }

    public void setCount_of_goods(Long count_of_goods) {
        this.count_of_goods = count_of_goods;
    }

    public String getGood_name() {
        return good_name;
    }

    public void setGood_name(String good_name) {
        this.good_name = good_name;
    }

    public String getPicture_file_name() {
        return picture_file_name;
    }

    public void setPicture_file_name(String picture_file_name) {
        this.picture_file_name = picture_file_name;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Good{" +
                "idGood=" + idGood +
                ", count_of_goods=" + count_of_goods +
                ", good_name='" + good_name + '\'' +
                ", picture_file_name='" + picture_file_name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
