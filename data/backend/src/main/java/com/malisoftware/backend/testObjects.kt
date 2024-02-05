package com.data.backend

import com.malisoftware.model.BusinessItems
import com.malisoftware.model.CategoryData
import com.malisoftware.model.Items
import com.malisoftware.model.ItemsList
import com.malisoftware.model.Sponsors
import com.malisoftware.model.Store
import com.malisoftware.model.BusinessData
import java.util.UUID

object TestObjects {
    val restaurants = listOf(
        BusinessData(
            id = UUID.randomUUID().toString(),
            title = "Burger house",
            category = "Burger",
            minPrice = 2000.00,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl.",
            feedback = "4.7",
            imageUrl = "https://www.shutterstock.com/image-photo/burger-tomateoes-lettuce-pickles-on-600nw-2309539129.jpg",
            promotion = "10% reduction",
            promotionDescription = "10% reduction on all orders min 10€",
            deliveryTime = 10,
            deliveryFee = 2000.00,
            isOpen = true,
            openingTime = "01:00:00.000000",
            closingTime = "20:00:00.000000",
            openingDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"),
            raterCount = 5000,
            goodRate = 5000,
            badRate = 0,
        ),
        BusinessData(
            id = UUID.randomUUID().toString(),
            title = "Pizza flow",
            category = "Pizza",
            minPrice = 1000.00,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl.",
            feedback = "2.4",
            imageUrl = "https://img.freepik.com/photos-gratuite/pizza-fraichement-italienne-tranche-fromage-mozzarella-ia-generative_188544-12347.jpg",
            promotion = "",
            promotionDescription = "",
            deliveryTime = 10,
            deliveryFee = 2500.00,
            isOpen = true,
            openingTime = "09:00:00.000000",
            closingTime = "20:00:00.000000",
            openingDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
            raterCount = 5000,
            goodRate = 5000,
            badRate = 0,
        ),
        BusinessData(
            id = UUID.randomUUID().toString(),
            title = "Sandwich king",
            category = "Sandwich",
            minPrice = 2000.00,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl.",
            feedback = "3.4",
            imageUrl = "https://istanbul.com/upload/istanbul-about-city-food-drink-street-food-turkish-durum-wrap-2.webp",
            promotion = "1 Sandwich Acherter 1 offert",
            promotionDescription = "",
            deliveryTime = 10,
            deliveryFee = 2000.00,
            isOpen = true,
            openingTime = "00:00:00.000000",
            closingTime = "23:59:00.000000",
            openingDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", ),
            raterCount = 5000,
            goodRate = 5000,
            badRate = 0,
        ),
        BusinessData(
            id = UUID.randomUUID().toString(),
            title = "Petit croissant",
            category = "Petit déjeuner",
            minPrice = 1000.00,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl.",
            feedback = "4.1",
            imageUrl = "https://happylyfe.in.th/wp-content/uploads/Almond-Croisant.jpg",
            promotion = "10% reduction",
            promotionDescription = "10% reduction on all orders min 3200XOF",
            deliveryTime = 10,
            deliveryFee = 1000.00,
            isOpen = true,
            openingTime = "10:00:00.000000",
            closingTime = "20:00:00.000000",
            openingDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"),
            raterCount = 5000,
            goodRate = 5000,
            badRate = 0,
        ),
        BusinessData(
            id = UUID.randomUUID().toString(),
            title = "Burger master",
            category = "Burger",
            minPrice = 2500.00,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl.",
            feedback = "5.0",
            imageUrl = "https://www.foodandwine.com/thmb/DI29Houjc_ccAtFKly0BbVsusHc=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/crispy-comte-cheesburgers-FT-RECIPE0921-6166c6552b7148e8a8561f7765ddf20b.jpg",
            promotion = "",
            promotionDescription = "",
            deliveryTime = 30,
            deliveryFee = 1500.00,
            isOpen = true,
            openingTime = "01:00:00.000000",
            closingTime = "23:59:00.000000",
            openingDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
            raterCount = 5000,
            goodRate = 3000,
            badRate = 2000,
            sponsored = true
        ),
        BusinessData(
            id = UUID.randomUUID().toString(),
            title = "Pizza king",
            category = "Pizza",
            minPrice = 2500.00,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl.",
            feedback = "4.9",
            imageUrl = "https://www.southernliving.com/thmb/3x3cJaiOvQ8-3YxtMQX0vvh1hQw=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/2652401_QFSSL_SupremePizza_00072-d910a935ba7d448e8c7545a963ed7101.jpg",
            promotion = "",
            promotionDescription = "",
            deliveryTime = 25,
            deliveryFee = 2000.00,
            isOpen = true,
            openingTime = "01:00:00.000000",
            closingTime = "20:00:00.000000",
            openingDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"),
            raterCount = 5000,
            goodRate = 4000,
            badRate = 1000,
            sponsored = true
        ),
        BusinessData(
            id = UUID.randomUUID().toString(),
            title = "chick chicken",
            category = "Poulet",
            minPrice = 3000.00,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl.",
            feedback = "4.9",
            imageUrl = "https://fr.frije.com/content/recipes/1291/800-1.jpg",
            promotion = "1000XOF de reduction",
            promotionDescription = "1000XOF de reduction sur toutes les commandes min 5000XOF",
            deliveryTime =25,
            deliveryFee = 500.00,
            isOpen = true,
            openingTime = "01:00:00.000000",
            closingTime = "20:00:00.000000",
            openingDays = listOf("Tuesday", "Wednesday", "Friday", "Saturday", "Sunday"),
            raterCount = 5000,
            goodRate = 4000,
            badRate = 1000,
            sponsored = true
        ),
        BusinessData(
            id = UUID.randomUUID().toString(),
            title = "Tchieke",
            category = "Africain",
            minPrice = 3000.00,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl.",
            feedback = "4.9",
            imageUrl = "https://i.pinimg.com/originals/da/ca/70/daca7056cf2547e927e14806641e3601.jpg",
            promotion = "1500XOF de reduction",
            promotionDescription = "1500XOF de reduction sur toutes les commandes min 5000XOF",
            deliveryTime = 25,
            deliveryFee = 500.00,
            isOpen = true,
            openingTime = "21:00:00.000000",
            closingTime = "23:00:00.000000",
            openingDays = listOf("Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"),
            raterCount = 5000,
            goodRate = 4000,
            badRate = 1000,
            sponsored = true
        ),
    )

    val shops = listOf(
        BusinessData(
            id = UUID.randomUUID().toString(),
            title = "Fruit Paradise",
            category = "Fruit",
            minPrice = 1000.00,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl. ",
            feedback = "4.7",
            imageUrl = "https://images.livemint.com/rf/Image-621x414/LiveMint/Period2/2017/10/31/Photos/Processed/fruits-kFLF--621x414@LiveMint.jpg",
            promotion = "",
            promotionDescription = "",
            deliveryTime = 15,
            deliveryFee = 0.0,
            isOpen = true,
            openingTime = "10:00:00.000000",
            closingTime = "20:00:00.000000",
            openingDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"),
            raterCount = 5000,
            goodRate = 3500,
            badRate = 0,
            isRestaurant = false,
        ),
        BusinessData(
            id = UUID.randomUUID().toString(),
            title = "Ko Market",
            category = "Tous",
            minPrice = 6000.00,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, ",
            feedback = "2.4",
            imageUrl = "https://blendsmooth.fr/wp-content/uploads/2020/12/fruits-et-legumes.jpg",
            promotion = "",
            promotionDescription = "",
            deliveryTime = 10,
            deliveryFee = 0.0,
            isOpen = true,
            openingTime = "09:00:00.000000",
            closingTime = "20:00:00.000000",
            openingDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"),
            raterCount = 5000,
            goodRate = 5000,
            badRate = 0,
            isRestaurant = false,
        ),
        BusinessData(
            isRestaurant = false,
            id = UUID.randomUUID().toString(),
            title = "Dibi douman",
            category = "Viande",
            minPrice = 2000.00,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies",
            imageUrl = "https://img.cuisineaz.com/1200x675/2018/08/10/i141648-boeuf.jpeg",
            feedback = "3.4",
            promotion = "",
            promotionDescription = "",
            deliveryTime = 10,
            deliveryFee = 2000.00,
            isOpen = true,
            openingTime = "02:00:00.000000",
            closingTime = "23:00:00.000000",
            openingDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", ),
            raterCount = 5000,
            goodRate = 5000,
            badRate = 0,
            sponsored = true
        ),
        BusinessData(
            id = UUID.randomUUID().toString(),
            title = "La Boulangerie",
            category = "Pain",
            minPrice = 1000.00,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies",
            feedback = "4.1",
            imageUrl = "https://img.cuisineaz.com/660x660/2013/12/20/i9204-recette-de-pain-de-campagne.jpeg",
            promotion = "",
            promotionDescription = "",
            deliveryTime = 15,
            deliveryFee = 1000.00,
            isOpen = true,
            openingTime = "01:00:00.000000",
            closingTime = "20:00:00.000000",
            openingDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"),
            raterCount = 5000,
            goodRate = 5000,
            badRate = 0,
            sponsored = true,
            isRestaurant = false,
        ),
        BusinessData(
            id = UUID.randomUUID().toString(),
            title = "Boisson naturelle",
            category = "Boisson",
            minPrice = 3000.00,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies",
            feedback = "5.0",
            imageUrl = "https://www.monvanityideal.com/data/nodes/37/43/u/boissons-bien-etre-fe58b598afb250ad195ba1d2f05c601e.jpg",
            promotion = "10% de reduction",
            promotionDescription = "10% de reduction sur toutes les commandes min 3200XOF",
            deliveryTime = 30,
            deliveryFee = 1500.00,
            isOpen = true,
            openingTime = "21:00:00.000000",
            closingTime = "23:59:00.000000",
            openingDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
            raterCount = 5000,
            goodRate = 3000,
            badRate = 2000,
            sponsored = true,
            isRestaurant = false,
        )
    )

    val categories = listOf(
        CategoryData(
            id = 1,
            title = "Burger",
            imageUrl = "https://media.istockphoto.com/id/1309352410/tr/foto%C4%9Fraf/ah%C5%9Fap-tahta-%C3%BCzerinde-domates-ve-marul-ile-%C3%A7izburger.jpg?s=612x612&w=0&k=20&c=T_95-FkIT_MeNDK6WrHwQDXRpdT7tdsW3iPTtFxoL4Y=",
            isRestaurant = true,
        ),
        CategoryData(
            id = 2,
            title = "Pizza",
            imageUrl = "https://assets.tmecosys.com/image/upload/t_web767x639/img/recipe/ras/Assets/ecaeb2cc-a950-4645-a648-9137305b3287/Derivates/df977b90-193d-49d4-a59d-8dd922bcbf65.jpg",
            isRestaurant = true,
        ),
        CategoryData(
            id = 3,
            title = "Sandwich",
            imageUrl = "https://www.thedailymeal.com/img/gallery/the-onion-hack-thats-perfect-for-making-shawarma-at-home/l-intro-1692883768.jpg",
            isRestaurant = true,
        ),
        CategoryData(
            id = 4,
            title = "Petit déjeuner",
            imageUrl = "https://i.ytimg.com/vi/VfU6xcolmoA/maxresdefault.jpg",
            isRestaurant = true,
        ),
        CategoryData(
            id = 5,
            title = "Poulet",
            imageUrl = "https://img.cuisineaz.com/660x660/2016/07/26/i100135-poulet-croustillant.jpeg",
            isRestaurant = true,
        ),
        CategoryData(
            id = 6,
            title = "Africain",
            imageUrl = "https://locavor.fr/data/produits/8/187547/attieke-poulet-bowl-187547-1662811340-1.jpg",
            isRestaurant = true,
        ),
        CategoryData(
            id = 7,
            title = "Fruit",
            imageUrl = "https://images.immediate.co.uk/production/volatile/sites/30/2023/02/Bowl-of-fruit-5155e6f.jpg?quality=90&resize=440,400",
            isRestaurant = false,
        ),
        CategoryData(
            id = 8,
            title = "Boisson",
            imageUrl = "https://img.cuisineaz.com/1024x768/2018/11/17/i144270-jus-d-orange.jpeg",
            isRestaurant = false,
        ),
        CategoryData(
            id = 9,
            title = "Pain",
            imageUrl = "https://mapatisserie.fr/wp-content/uploads/2020/06/recette-pain-de-campagne-20200603_201946-01-scaled.jpeg",
            isRestaurant = false,
        ),
        CategoryData(
            id = 10,
            title = "Viande",
            imageUrl = "https://ds.static.rtbf.be/article/image/1920x1080/5/f/d/ba3c736667394d5082f86f28aef38107-1529669795.jpg",
            isRestaurant = false,
        )
    )

    val stores = listOf(
        Store(
            id = "1",
            name = "Market",
            imageUrl = "https://cdhf.ca/wp-content/uploads/2023/03/CDHF-TALKBANNERS-2000-%C3%97-900-px-27.png",
        ),
        Store(
            id = "2",
            name = "Restaurant",
            imageUrl = "https://cdhf.ca/wp-content/uploads/2023/01/fast-food.png",
        ),

    )

    val sponsors = listOf(
        Sponsors(
            id = 1,
            imageUrl = "https://blog.lusso.fr/wp-content/uploads/2015/07/5-fruit-legume-jour-moche-pub.jpg",
        ),
        Sponsors(
            id = 2,
            imageUrl = "https://i.ytimg.com/vi/ci_bVS73GGE/maxresdefault.jpg",
        ),
        Sponsors(
            id = 3,
            imageUrl = "https://i.ytimg.com/vi/jEU_rmVIhrc/maxresdefault.jpg",
        ),

    )
}

object TestItemObject{
    private val menuItems = listOf(
        ItemsList(
            title = "Burgers",
            items = listOf(
                Items(
                    id = 1,
                    title = "Classic Burger",
                    price = 2000.00,
                    promotion = "10% reduction",
                    imageUrl = "https://mccormick.widen.net/content/mrqnc4vbjy/original/frenchs_burger_styled-image_800x800.png",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl.",
                ),
                Items(
                    id = 2,
                    title = "Cheeseburger",
                    promotion = "buy 1 get 1 free",
                    imageUrl = "https://recipes.net/wp-content/uploads/2023/05/hardees-double-cheeseburger-recipe_d48b79ef43b714e98a3ad95a7ab9e12e-768x768.jpeg",
                    price = 2500.00,
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl.",
                ),
                Items(
                    id = 3,
                    title = "Bacon Burger",
                    imageUrl = "https://tatyanaseverydayfood.com/wp-content/uploads/2021/06/Caramelized-Onion-Bacon-Cheeseburger-Recipe.jpg",
                    price = 2800.00,
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl.",
                ),
                // Add more burger items as needed
            )
        ),
        ItemsList(
            title = "Fries",
            items = listOf(
                Items(
                    id = 4,
                    title = "Regular Fries",
                    promotion = "buy 1 get 1 free" ,
                    imageUrl = "https://images.squarespace-cdn.com/content/v1/57f1462015d5dbf1ae364486/1537703834286-0YZEDZDGVS2CRMY21O0B/sweet+potato+fries+or+white+potato+fries+which+is+better+vegan+nutrition+nutritionist.jpg",
                    price = 1000.00,
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl.",
                ),
                Items(
                    id = 5,
                    title = "Curly Fries",
                    imageUrl = "https://cdn.theliveinkitchen.com/wp-content/uploads/2022/05/03150451/Frozen-Curly-Fries-Air-Fryer-The-Live-In-Kitchen-Featured.jpg",
                    price = 1200.00,
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl.",
                ),
                Items(
                    id = 6,
                    title = "Sweet Potato Fries",
                    imageUrl = "https://www.cookingclassy.com/wp-content/uploads/2021/10/baked-sweet-potato-fries-12.jpg",
                    price = 1500.00,
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl.",
                ),
                // Add more fries items as needed
            )
        ),
        ItemsList(
            title = "Drinks",
            items = listOf(
                Items(
                    id = 7,
                    title = "Soda",
                    imageUrl = "https://ardenmarket.com.tr/media/catalog/product/cache/0b154ba1d9ffbc98998b9163ce10b49b/b/e/beypazari-soda-200ml.jpg",
                    price = 500.00,
                    promotion = "buy 1 get 1 free" ,
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl.",
                ),
                Items(
                    id = 8,
                    title = "Iced Tea",
                    imageUrl = "https://food.fnr.sndimg.com/content/dam/images/food/fullset/2011/6/3/1/FNM_070111-Centerfold-007_s4x3.jpg.rend.hgtvcom.1280.1280.suffix/1371597847872.jpeg",
                    price = 700.00,
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl.",
                ),
                // Add more drink items as needed
            )
        ),
        ItemsList(
            title = "Dessert",
            items = listOf(
                Items(
                    id = 9,
                    title = "Salad",
                    promotion = "20% de reduction",
                    imageUrl = "https://www.eatingwell.com/thmb/rmLlvSjdnJCCy_7iqqj3x7XS72c=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/chopped-power-salad-with-chicken-0ad93f1931524a679c0f8854d74e6e57.jpg",
                    price = 1200.00,
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl.",
                ),
                Items(
                    id = 10,
                    title = "Dessert",
                    imageUrl = "https://www.shutterstock.com/image-photo/choux-cake-pistachio-cream-nuts-600nw-2361934171.jpg",
                    price = 800.00,
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl.",
                ),
                // Add more items in the "Other" category as needed
            )
        )
        // Add more categories and items as needed
    )

    private val pizzaMenu = listOf(
        ItemsList(
            title = "Classic Pizzas",
            items = listOf(
                Items(
                    id = 1,
                    title = "Margherita",
                    promotion = "10% de reduction",
                    imageUrl = "https://www.blossmangas.com/wp-content/uploads/2021/05/Margherita-pizza-2.jpg",
                    description = "Classic tomato, mozzarella, and basil.",
                    price = 1200.99
                ),
                Items(
                    id = 2,
                    title = "Pepperoni",
                    imageUrl = "https://fr.ooni.com/cdn/shop/articles/Ooni_Diablo_resized.jpg?crop=center&height=1200&v=1616496838&width=1200",
                    description = "Tomato sauce, mozzarella, and pepperoni.",
                    price = 1400.99
                ),
                Items(
                    id = 3,
                    title = "Vegetarian",
                    imageUrl = "https://cdn.loveandlemons.com/wp-content/uploads/2018/09/vegan-pizza.jpg",
                    description = "Tomato sauce, mozzarella, bell peppers, onions, mushrooms, and olives.",
                    price = 1500.99
                ),
                // Add more classic pizza items as needed
            )
        ),
        ItemsList(
            title = "Specialty Pizzas",
            items = listOf(
                Items(
                    id = 4,
                    title = "BBQ Chicken",
                    imageUrl = "https://www.loveandotherspices.com/wp-content/uploads/2022/04/air-fryer-bbq-chicken-main.jpg",
                    description = "BBQ sauce, mozzarella, grilled chicken, red onions, and cilantro.",
                    price = 1600.99
                ),
                Items(
                    id = 5,
                    title = "Hawaiian",
                    description = "Tomato sauce, mozzarella, ham, pineapple, and bacon.",
                    price = 1700.99
                ),
                Items(
                    id = 6,
                    title = "Meat Lovers",
                    imageUrl = "https://www.kayscleaneats.com/wp-content/uploads/2020/07/unadjustednonraw_thumb_a8b0-480x360.jpg",
                    description = "Tomato sauce, mozzarella, pepperoni, sausage, bacon, and ground beef.",
                    price = 1800.99
                ),
                // Add more specialty pizza items as needed
            )
        ),
        // Add more pizza categories and items as needed
    )

    private val sandwichMenu = listOf(
        ItemsList(
            title = "Classic Sandwiches",
            items = listOf(
                Items(
                    id = 1,
                    title = "Turkey Club",
                    imageUrl = "https://food.fnr.sndimg.com/content/dam/images/food/fullset/2021/07/16/0/FNM_090121-Turkey-Club_s4x3.jpg.rend.hgtvcom.1280.1280.suffix/1626465609122.jpeg",
                    description = "Turkey, bacon, lettuce, tomato, and mayo on toasted bread.",
                    price = 900.99
                ),
                Items(
                    id = 2,
                    title = "BLT",
                    imageUrl = "https://somethingaboutsandwiches.com/wp-content/uploads/2020/07/blt-sandwich.jpg",
                    description = "Bacon, lettuce, tomato, and mayo on toasted bread.",
                    price = 800.99
                ),
                Items(
                    id = 3,
                    title = "Grilled Cheese",
                    promotion = "10% de reduction",
                    imageUrl = "https://thealmondeater.com/wp-content/uploads/2022/05/brie-grilled-cheese_web-6.jpg",
                    description = "Melted cheddar cheese on toasted bread.",
                    price = 799.99
                ),
                // Add more classic sandwich items as needed
            )
        ),
        ItemsList(
            title = "Signature Sandwiches",
            items = listOf(
                Items(
                    id = 4,
                    title = "Chicken Avocado Wrap",
                    imageUrl = "https://veronikaskitchen.com/wp-content/uploads/2020/03/20200226-IMG_2517.jpg",
                    description = "Grilled chicken, avocado, lettuce, tomato, and ranch dressing wrapped in a tortilla.",
                    price = 1000.99
                ),
                Items(
                    id = 5,
                    title = "Italian Sub",
                    imageUrl = "https://images.squarespace-cdn.com/content/v1/5e80e64f9327941b94517c20/1663635990195-U70NTD4TI9V6EZS7H7CA/Italian+Combo.jpg",
                    description = "Ham, salami, pepperoni, provolone cheese, lettuce, tomato, onion, and Italian dressing on a sub roll.",
                    price = 1199.99
                ),
                Items(
                    id = 6,
                    title = "Veggie Delight",
                    imageUrl = "https://img.freepik.com/photos-premium/veggie-delight-liberez-plaisir-abondance-morceaux-legumes-visibles_971713-420.jpg",
                    description = "Grilled vegetables, hummus, lettuce, tomato, and feta cheese on multigrain bread.",
                    price = 999.99
                ),
                // Add more signature sandwich items as needed
            )
        ),
    )

    private val croissantMenu = listOf(
        ItemsList(
            title = "Classic Petit Croissants",
            items = listOf(
                Items(
                    id = 1,
                    title = "Plain",
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjX66UZAViAfck8FZxylpHLW0wmbXsL3wnEA&usqp=CAU",
                    description = "A classic flaky croissant without any additional filling.",
                    price = 299.99
                ),
                Items(
                    id = 2,
                    title = "Chocolate",
                    imageUrl = "https://res.cloudinary.com/hksqkdlah/image/upload/SFS_Chocolate_Croissants_035_xmghbe.jpg",
                    description = "A buttery croissant filled with rich chocolate.",
                    price = 399.99
                ),
                Items(
                    id = 3,
                    title = "Almond",
                    promotion = "10% de reduction",
                    imageUrl = "https://www.miflavour.com/cdn/shop/products/almond-croissant-morning-pastry-patissserie_1200x1200.jpg?v=1616116319",
                    description = "A sweet croissant filled with almond paste and topped with sliced almonds.",
                    price = 499.99
                ),
                // Add more classic petit croissant items as needed
            )
        ),
        ItemsList(
            title = "Specialty Petit Croissants",
            items = listOf(
                Items(
                    id = 4,
                    title = "Strawberry Cream",
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjAfdQLxrGnlyAilavdMv3pUohQmlRRL-gQ5_NFAQFsr9kax7xZ_b6EotD_vizh1QgQHo&usqp=CAU",
                    description = "A delightful croissant filled with strawberry cream.",
                    price = 499.49
                ),
                Items(
                    id = 5,
                    title = "Ham and Cheese",
                    imageUrl = "https://az727718.vo.msecnd.net/c262cdbb171e45e48ac6851141eed6c2/images/008d5589e6534dfd9b4cb7297f44f5a7@2x.png",
                    description = "A savory croissant with ham and melted cheese.",
                    price = 599.49
                ),
                Items(
                    id = 6,
                    title = "Raspberry Danish",
                    imageUrl = "https://i.pinimg.com/736x/4d/ec/48/4dec488b2015c665cd8cb762896c7b04.jpg",
                    description = "A flaky croissant with a raspberry filling, topped with icing.",
                    price = 499.99
                ),
                )
        ),
    )

    private val burgerMasterMenu = listOf(
        ItemsList(
            title = "Classic Burgers",
            items = listOf(
                Items(
                    id = 1,
                    title = "Burger Master Classic",
                    description = "Classic beef patty, lettuce, tomato, onion, pickles, and special sauce.",
                    price = 999.99
                ),
                Items(
                    id = 2,
                    title = "Cheese Lover's Burger",
                    description = "Beef patty, melted cheddar cheese, lettuce, tomato, and mayo.",
                    price = 1099.99
                ),
                Items(
                    id = 3,
                    title = "Bacon Deluxe",
                    description = "Beef patty, crispy bacon, lettuce, tomato, onion, and BBQ sauce.",
                    price = 1199.99
                ),
                // Add more classic burger items as needed
            )
        ),
        ItemsList(
            title = "Signature Burgers",
            items = listOf(
                Items(
                    id = 4,
                    title = "Spicy Inferno",
                    description = "Spicy beef patty, pepper jack cheese, jalapeños, lettuce, and chipotle mayo.",
                    price = 1299.99
                ),
                Items(
                    id = 5,
                    title = "Avocado Bliss",
                    description = "Beef patty, avocado slices, lettuce, tomato, and garlic aioli.",
                    price = 1399.99
                ),
                Items(
                    id = 6,
                    title = "Mushroom Swiss Supreme",
                    description = "Beef patty, sautéed mushrooms, Swiss cheese, lettuce, and truffle mayo.",
                    price = 1499.99
                ),
                // Add more signature burger items as needed
            )
        ),
    )

    private val pizzaKingMenu = listOf (
        ItemsList(
            title = "Classic Pizzas",
            items = listOf(
                Items(
                    id = 1,
                    title = "Margarita",
                    imageUrl = "https://ohsweetbasil.com/wp-content/uploads/four-cheese-margherita-pizza-recipe-12-scaled.jpg",
                    description = "Classic tomato, mozzarella, and basil.",
                    price = 1299.99
                ),
                Items(
                    id = 2,
                    title = "Pepperoni Feast",
                    imageUrl = "https://i.pinimg.com/originals/a4/65/06/a465068d51da74380d01c79b2f575dfa.jpg",
                    description = "Tomato sauce, mozzarella, and a generous amount of pepperoni.",
                    price = 1499.99
                ),
                Items(
                    id = 3,
                    title = "Vegetarian Delight",
                    imageUrl = "https://sonipizza.co/wp-content/uploads/2020/06/tomaoto.jpg",
                    description = "Tomato sauce, mozzarella, bell peppers, onions, mushrooms, and olives.",
                    price = 1599.99
                ),
                // Add more classic pizza items as needed
            )
        ),
        ItemsList(
            title = "Specialty Pizzas",
            items = listOf(
                Items(
                    id = 4,
                    title = "BBQ Chicken Supreme",
                    imageUrl = "https://bakingmischief.com/wp-content/uploads/2020/08/bbq-chicken-pizza-image-square-2.jpg",
                    description = "BBQ sauce, mozzarella, grilled chicken, red onions, and cilantro.",
                    price = 1699.99
                ),
                Items(
                    id = 5,
                    title = "Hawaiian Bliss",
                    imageUrl = "https://img.cdn4dd.com/p/fit=cover,width=1200,height=1200,format=auto,quality=90/media/photosV2/143d3000-2f0b-4aeb-abc4-731e155de7a9-retina-large.jpg",
                    description = "Tomato sauce, mozzarella, ham, pineapple, and bacon.",
                    price = 1799.99
                ),
                Items(
                    id = 6,
                    title = "Meat Lovers Extravaganza",
                    imageUrl = "https://familyaroundthetable.com/wp-content/uploads/2020/09/skillet-pizza-overhead-whole-2.jpg",
                    description = "Tomato sauce, mozzarella, pepperoni, sausage, bacon, and ground beef.",
                    price = 1899.99
                ),
                // Add more specialty pizza items as needed
            )
        ),
        // Add more pizza categories and items as needed
    )

    private val chickChickenMenu = listOf(
        ItemsList(
            title = "Classic Chicken Dishes",
            items = listOf(
                Items(
                    id = 1,
                    title = "Grilled Chicken Breast",
                    imageUrl = "https://assets.epicurious.com/photos/629e61c3c8fc75488633ba7d/1:1/w_4102,h_4102,c_limit/GrilledChickenBreast_RECIPE_052522_34925.jpg",
                    description = "Juicy grilled chicken breast seasoned to perfection.",
                    price = 1099.99
                ),
                Items(
                    id = 2,
                    title = "Fried Chicken Wings",
                    imageUrl = "https://www.krumpli.co.uk/wp-content/uploads/2023/08/Crispy-Fried-Buttermilk-Chicken-Wings-01.jpg",
                    description = "Crispy fried chicken wings with your choice of sauce.",
                    price = 1299.99
                ),
                Items(
                    id = 3,
                    title = "Chicken Tenders Basket",
                    imageUrl = "https://www.simplejoy.com/wp-content/uploads/2021/03/Air-Fryer-Chicken-Tenders-683x1024.webp",
                    description = "Tender and golden-brown chicken tenders served with dipping sauce.",
                    price = 1199.99
                ),
                // Add more classic chicken dishes as needed
            )
        ),
        ItemsList(
            title = "Signature Chicken Specialties",
            items = listOf(
                Items(
                    id = 4,
                    title = "Spicy Buffalo Chicken Sandwich",
                    imageUrl = "https://somethingaboutsandwiches.com/wp-content/uploads/2021/03/crispy-buffalo-chicken-sandwich.jpg",
                    description = "Spicy buffalo chicken breast, lettuce, tomato, and ranch dressing on a bun.",
                    price = 1399.99
                ),
                Items(
                    id = 5,
                    title = "Honey Mustard Glazed Chicken",
                    imageUrl = "https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2015/08/Baked-Honey-Mustard-Chicken-8.jpg",
                    description = "Grilled chicken glazed with honey mustard, served with rice and vegetables.",
                    price = 1499.99
                ),
                Items(
                    id = 6,
                    title = "Crispy Chicken Caesar Salad",
                    imageUrl = "https://s23209.pcdn.co/wp-content/uploads/2023/01/220905_DD_Chx-Caesar-Salad_051.jpg",
                    description = "Crispy chicken strips, romaine lettuce, croutons, and Caesar dressing.",
                    price = 1299.99
                ),
                // Add more signature chicken specialties as needed
            )
        ),
        // Add more chicken categories and items as needed
    )

    private val tchiekeMenu = listOf(
        ItemsList(
            title = "Classic Tchieke Dishes",
            items = listOf(
                Items(
                    id = 1,
                    title = "Atieke Poulet",
                    imageUrl = "https://voiedefemme.net/wp-content/uploads/2021/07/cuisine.jpg",
                    description = "Description of the first Tchieke dish.",
                    price = 1299.99
                ),
                Items(
                    id = 2,
                    title = "Atieke Poisson",
                    imageUrl = "https://www.cuisinedecheznous.net/wp-content/uploads/2021/10/117163947_1647146982104283_6612211351728648199_n.jpg",
                    description = "Description of the second Tchieke dish.",
                    price = 1499.99
                ),
                // Add more classic Tchieke dishes as needed
            )
        ),
        ItemsList(
            title = "Alloco",
            items = listOf(
                Items(
                    id = 3,
                    title = "Atieke Alloco",
                    imageUrl = "https://oswaldkouame.jp/wp-content/uploads/2018/02/Atti%C3%A9k%C3%A9-with-Alloco.jpg",
                    description = "Description of the first special Tchieke dish.",
                    price = 1599.99
                ),
                Items(
                    id = 4,
                    title = "Alloco Poisson",
                    imageUrl = "https://www.linfodrome.com/media//article/images/src/59575-8d929d80ab3c4bb235557267c12f147e.webp",
                    description = "Description of the second special Tchieke dish.",
                    price = 1699.99
                ),
            )
        ),
    )

    val restaurantItems = listOf(
        BusinessItems(
            businessId = TestObjects.restaurants[0].id,
            items = menuItems
        ),
        BusinessItems(
            businessId = TestObjects.restaurants[1].id,
            items = pizzaMenu
        ),
        BusinessItems(
            businessId = TestObjects.restaurants[2].id,
            items = sandwichMenu
        ),
        BusinessItems(
            businessId = TestObjects.restaurants[3].id,
            items = croissantMenu
        ),
        BusinessItems(
            businessId = TestObjects.restaurants[4].id,
            items = burgerMasterMenu
        ),
        BusinessItems(
            businessId = TestObjects.restaurants[5].id,
            items = pizzaKingMenu
        ),
        BusinessItems(
            businessId = TestObjects.restaurants[6].id,
            items = chickChickenMenu
        ),
        BusinessItems(
            businessId = TestObjects.restaurants[7].id,
            items = tchiekeMenu
        ),
    )
}