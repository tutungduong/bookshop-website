package com.bookshop.constant;

import java.util.List;

public interface SearchFields {
     List<String> CATEGORY = List.of(
            "name",
            "description"
    );

     List<String> USER = List.of(
            "username",
            "fullname",
            "email",
            "phone",
            "address"
    );

     List<String> ROLE = List.of(
            "code",
            "name"
    );

      List<String> PRODUCT = List.of(
            "name",
            "code",
            "description",
            "author",
            "publisher",
            "publishedYear",
            "pages",
            "category.name"
    );

      List<String> VARIANT = List.of(
            "product.name",
            "product.code"
    );

       List<String> IMAGE = List.of(
            "name",
            "contentType"
    );

        List<String> ORDER_CANCELLATION_REASON = List.of(
            "name"
    );

         List<String> ORDER = List.of(
            "code",
            "toName",
            "toPhone",
            "toAddress",
            "toWardName",
            "user.username",
            "user.fullname"
    );

         List<String> ORDER_VARIANT = List.of(
            "order.code",
            "variant.product.name",
            "variant.product.code",
            "variant.cost",
            "variant.price",
            "price",
            "quantity",
            "amount"
    );

         List<String> CLIENT_PRODUCT = List.of(
            "name",
            "category.name"
    );

}
