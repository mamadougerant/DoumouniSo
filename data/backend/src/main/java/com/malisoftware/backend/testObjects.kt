package com.malisoftware.backend

import com.malisoftware.backend.enumclasses.ShopCategories
import com.malisoftware.model.BusinessItems
import com.malisoftware.model.CategoryData
import com.malisoftware.model.Items
import com.malisoftware.model.ItemsList
import com.malisoftware.model.Sponsors
import com.malisoftware.model.Store
import com.malisoftware.model.BusinessData
import com.malisoftware.model.ShopItemCategories

object TestObjects {
    val restaurants = listOf(
        BusinessData(
            id = "Burger house",
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
            id = "Pizza flow",
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
            id = "Sandwich king",
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
            id = "Petit croissant",
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
            id = "Burger master",
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
            id = "Pizza king",
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
            closingTime = "23:00:00.000000",
            openingDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"),
            raterCount = 5000,
            goodRate = 4000,
            badRate = 1000,
            sponsored = true
        ),
        BusinessData(
            id = "chick chicken",
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
            id = "Tchieke",
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
            id = "Fruit Paradise",
            title = "Fruit Paradise",
            category = ShopCategories.GROCERY.value,
            minPrice = 1000.00,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, nisl nisl aliquet nisl, vitae aliquam nisl nisl eget nisl. ",
            feedback = "4.7",
            imageUrl = "https://images.livemint.com/rf/Image-621x414/LiveMint/Period2/2017/10/31/Photos/Processed/fruits-kFLF--621x414@LiveMint.jpg",
            promotion = "",
            promotionDescription = "",
            deliveryTime = 15,
            deliveryFee = 0.0,
            isOpen = true,
            openingTime = "01:00:00.000000",
            closingTime = "20:00:00.000000",
            openingDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"),
            raterCount = 5000,
            goodRate = 3500,
            badRate = 0,
            isRestaurant = false,
        ),
        BusinessData(
            id = "Ko Market",
            title = "Ko Market",
            category = ShopCategories.SUPERMARKET.value,
            minPrice = 6000.00,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies, ",
            feedback = "2.4",
            imageUrl = "https://blendsmooth.fr/wp-content/uploads/2020/12/fruits-et-legumes.jpg",
            promotion = "",
            promotionDescription = "",
            deliveryTime = 10,
            deliveryFee = 0.0,
            isOpen = true,
            openingTime = "01:00:00.000000",
            closingTime = "23:50:00.000000",
            openingDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday","Sunday"),
            raterCount = 5000,
            goodRate = 5000,
            badRate = 0,
            sponsored = true,
            isRestaurant = false,
        ),
        BusinessData(
            isRestaurant = false,
            id = "Soko Market",
            title = "Soko Market",
            category = ShopCategories.BUTCHERY.value,
            minPrice = 2000.00,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies",
            imageUrl = "https://img.cuisineaz.com/1200x675/2018/08/10/i141648-boeuf.jpeg",
            feedback = "3.4",
            promotion = "",
            promotionDescription = "",
            deliveryTime = 10,
            deliveryFee = 2000.00,
            isOpen = true,
            openingTime = "01:00:00.000000",
            closingTime = "23:00:00.000000",
            openingDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday","Sunday" ),
            raterCount = 5000,
            goodRate = 5000,
            badRate = 0,
            sponsored = true
        ),
        BusinessData(
            id = "AZ Market",
            title = "AZ Market",
            category = ShopCategories.SUPERMARKET.value,
            minPrice = 1000.00,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies",
            feedback = "4.1",
            imageUrl = "https://entrackr.com/storage/2020/03/grocery-store.jpg",
            promotion = "",
            promotionDescription = "",
            deliveryTime = 15,
            deliveryFee = 1000.00,
            isOpen = true,
            openingTime = "10:00:00.000000",
            closingTime = "20:00:00.000000",
            openingDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", ),
            raterCount = 5000,
            goodRate = 5000,
            badRate = 0,
            sponsored = true,
            isRestaurant = false,
        ),
        BusinessData(
            id = "Mali books",
            title = "Mali books",
            category = ShopCategories.BOOKS.value,
            minPrice = 3000.00,
            description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec euismod, nisl eget aliquam ultricies",
            feedback = "5.0",
            imageUrl = "https://media.lesechos.com/api/v1/images/view/608275088fe56f28b848df36/1280x720/061873913752-web-tete.jpg",
            promotion = "10% de reduction",
            promotionDescription = "10% de reduction sur toutes les commandes min 3200XOF",
            deliveryTime = 30,
            deliveryFee = 1500.00,
            isOpen = true,
            openingTime = "01:00:00.000000",
            closingTime = "23:59:00.000000",
            openingDays = listOf("Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Sunday"),
            raterCount = 5000,
            goodRate = 3000,
            badRate = 2000,
            sponsored = false,
            isRestaurant = false,
        )
    )
    // TODO assure toi la meme valeur de string est passe en enreigidtrant dans le db ShopCategories.SUPERMARKET.value
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
            title = ShopCategories.SUPERMARKET.value,
            imageUrl = "https://www.statcan.gc.ca/o1/sites/default/files/2023-06/CPI%20basket-article.jpg",
            isRestaurant = false,
        ),
        CategoryData(
            id = 8,
            title = ShopCategories.BUTCHERY.value,
            imageUrl = "https://www.palatesensations.com/images/68aff326cbd69035d5b9000cd7fe3e5c.jpg",
            isRestaurant = false,
        ),
        CategoryData(
            id = 9,
            title = ShopCategories.BOOKS.value,
            imageUrl = "https://i.tribune.com.pk/media/images/505909-Books-1360629521/505909-Books-1360629521.JPG",
            isRestaurant = false,
        ),
        CategoryData(
            id = 10,
            title = ShopCategories.GROCERY.value,
            imageUrl = "https://www.educatout.com/images/La-mathematique-du-groupe-des-legumes-et-fruits.jpg" ,
            isRestaurant = false,
        )
    )

    val stores = listOf(
        Store(
            id = "1",
            name = "Market",
            imageUrl = "https://awitid.ma/wp-content/uploads/2022/11/panier-medium-1200x675-cropped.png",
        ),
        Store(
            id = "2",
            name = "Restaurant",
            imageUrl = "https://e7.pngegg.com/pngimages/3/635/png-clipart-table-knife-fork-plate-cutlery-fork-and-knife-kitchen-fork-thumbnail.png",
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
                    description = "Lorem ipsum nise aliquam nisl nisl eget nisl.",
                ),
                Items(
                    id = 2,
                    title = "Cheeseburger",
                    promotion = "buy 1 get 1 free",
                    imageUrl = "https://recipes.net/wp-content/uploads/2023/05/hardees-double-cheeseburger-recipe_d48b79ef43b714e98a3ad95a7ab9e12e-768x768.jpeg",
                    price = 2500.00,
                    description = "Lorem ipsum nise aliquam nisl nisl eget nisl.",
                ),
                Items(
                    id = 3,
                    title = "Bacon Burger",
                    imageUrl = "https://tatyanaseverydayfood.com/wp-content/uploads/2021/06/Caramelized-Onion-Bacon-Cheeseburger-Recipe.jpg",
                    price = 2800.00,
                    description = "Lorem ipsum nise aliquam nisl nisl eget nisl.",
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
                    description = "Lorem ipsum nise aliquam nisl nisl eget nisl.",
                ),
                Items(
                    id = 5,
                    title = "Curly Fries",
                    imageUrl = "https://cdn.theliveinkitchen.com/wp-content/uploads/2022/05/03150451/Frozen-Curly-Fries-Air-Fryer-The-Live-In-Kitchen-Featured.jpg",
                    price = 1200.00,
                    description = "Lorem ipsum nise aliquam nisl nisl eget nisl.",
                ),
                Items(
                    id = 6,
                    title = "Sweet Potato Fries",
                    imageUrl = "https://www.cookingclassy.com/wp-content/uploads/2021/10/baked-sweet-potato-fries-12.jpg",
                    price = 1500.00,
                    description = "Lorem ipsum nise aliquam nisl nisl eget nisl.",
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
                    description = "Lorem ipsum nise aliquam nisl nisl eget nisl.",
                ),
                Items(
                    id = 8,
                    title = "Iced Tea",
                    imageUrl = "https://food.fnr.sndimg.com/content/dam/images/food/fullset/2011/6/3/1/FNM_070111-Centerfold-007_s4x3.jpg.rend.hgtvcom.1280.1280.suffix/1371597847872.jpeg",
                    price = 700.00,
                    description = "Lorem ipsum nise aliquam nisl nisl eget nisl.",
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
                    description = "Lorem ipsum nise aliquam nisl nisl eget nisl.",
                ),
                Items(
                    id = 10,
                    title = "Dessert",
                    imageUrl = "https://www.shutterstock.com/image-photo/choux-cake-pistachio-cream-nuts-600nw-2361934171.jpg",
                    price = 800.00,
                    description = "Lorem ipsum nise aliquam nisl nisl eget nisl.",
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
                    id = 11,
                    title = "Margherita",
                    promotion = "10% de reduction",
                    imageUrl = "https://www.blossmangas.com/wp-content/uploads/2021/05/Margherita-pizza-2.jpg",
                    description = "Classic tomato, mozzarella, and basil.",
                    price = 1200.99
                ),
                Items(
                    id = 12,
                    title = "Pepperoni",
                    imageUrl = "https://fr.ooni.com/cdn/shop/articles/Ooni_Diablo_resized.jpg?crop=center&height=1200&v=1616496838&width=1200",
                    description = "Tomato sauce, mozzarella, and pepperoni.",
                    price = 1400.99
                ),
                Items(
                    id = 13,
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
                    id = 14,
                    title = "BBQ Chicken",
                    imageUrl = "https://www.loveandotherspices.com/wp-content/uploads/2022/04/air-fryer-bbq-chicken-main.jpg",
                    description = "BBQ sauce, mozzarella, grilled chicken, red onions, and cilantro.",
                    price = 1600.99
                ),
                Items(
                    id = 15,
                    title = "Hawaiian",
                    description = "Tomato sauce, mozzarella, ham, pineapple, and bacon.",
                    price = 1700.99
                ),
                Items(
                    id = 16,
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
                    id = 21,
                    title = "Turkey Club",
                    imageUrl = "https://food.fnr.sndimg.com/content/dam/images/food/fullset/2021/07/16/0/FNM_090121-Turkey-Club_s4x3.jpg.rend.hgtvcom.1280.1280.suffix/1626465609122.jpeg",
                    description = "Turkey, bacon, lettuce, tomato, and mayo on toasted bread.",
                    price = 900.99
                ),
                Items(
                    id = 22,
                    title = "BLT",
                    imageUrl = "https://somethingaboutsandwiches.com/wp-content/uploads/2020/07/blt-sandwich.jpg",
                    description = "Bacon, lettuce, tomato, and mayo on toasted bread.",
                    price = 800.99
                ),
                Items(
                    id = 23,
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
                    id = 24,
                    title = "Chicken Avocado Wrap",
                    imageUrl = "https://veronikaskitchen.com/wp-content/uploads/2020/03/20200226-IMG_2517.jpg",
                    description = "Grilled chicken, avocado, lettuce, tomato, and ranch dressing wrapped in a tortilla.",
                    price = 1000.99
                ),
                Items(
                    id = 25,
                    title = "Italian Sub",
                    imageUrl = "https://images.squarespace-cdn.com/content/v1/5e80e64f9327941b94517c20/1663635990195-U70NTD4TI9V6EZS7H7CA/Italian+Combo.jpg",
                    description = "Ham, salami, pepperoni, provolone cheese, lettuce, tomato, onion, and Italian dressing on a sub roll.",
                    price = 1199.99
                ),
                Items(
                    id = 26,
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
                    id = 31,
                    title = "Plain",
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjX66UZAViAfck8FZxylpHLW0wmbXsL3wnEA&usqp=CAU",
                    description = "A classic flaky croissant without any additional filling.",
                    price = 299.99
                ),
                Items(
                    id = 32,
                    title = "Chocolate",
                    imageUrl = "https://res.cloudinary.com/hksqkdlah/image/upload/SFS_Chocolate_Croissants_035_xmghbe.jpg",
                    description = "A buttery croissant filled with rich chocolate.",
                    price = 399.99
                ),
                Items(
                    id = 33,
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
                    id = 34,
                    title = "Strawberry Cream",
                    imageUrl = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRjAfdQLxrGnlyAilavdMv3pUohQmlRRL-gQ5_NFAQFsr9kax7xZ_b6EotD_vizh1QgQHo&usqp=CAU",
                    description = "A delightful croissant filled with strawberry cream.",
                    price = 499.49
                ),
                Items(
                    id = 35,
                    title = "Ham and Cheese",
                    imageUrl = "https://az727718.vo.msecnd.net/c262cdbb171e45e48ac6851141eed6c2/images/008d5589e6534dfd9b4cb7297f44f5a7@2x.png",
                    description = "A savory croissant with ham and melted cheese.",
                    price = 599.49
                ),
                Items(
                    id = 36,
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
                    id = 41,
                    title = "Burger Master Classic",
                    description = "Classic beef patty, lettuce, tomato, onion, pickles, and special sauce.",
                    price = 999.99
                ),
                Items(
                    id = 42,
                    title = "Cheese Lover's Burger",
                    description = "Beef patty, melted cheddar cheese, lettuce, tomato, and mayo.",
                    price = 1099.99
                ),
                Items(
                    id = 43,
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
                    id = 44,
                    title = "Spicy Inferno",
                    description = "Spicy beef patty, pepper jack cheese, jalapeños, lettuce, and chipotle mayo.",
                    price = 1299.99
                ),
                Items(
                    id = 45,
                    title = "Avocado Bliss",
                    description = "Beef patty, avocado slices, lettuce, tomato, and garlic aioli.",
                    price = 1399.99
                ),
                Items(
                    id = 46,
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
                    id = 51,
                    title = "Margarita",
                    imageUrl = "https://ohsweetbasil.com/wp-content/uploads/four-cheese-margherita-pizza-recipe-12-scaled.jpg",
                    description = "Classic tomato, mozzarella, and basil.",
                    price = 1299.99
                ),
                Items(
                    id = 52,
                    title = "Pepperoni Feast",
                    imageUrl = "https://i.pinimg.com/originals/a4/65/06/a465068d51da74380d01c79b2f575dfa.jpg",
                    description = "Tomato sauce, mozzarella, and a generous amount of pepperoni.",
                    price = 1499.99
                ),
                Items(
                    id = 53,
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
                    id = 54,
                    title = "BBQ Chicken Supreme",
                    imageUrl = "https://bakingmischief.com/wp-content/uploads/2020/08/bbq-chicken-pizza-image-square-2.jpg",
                    description = "BBQ sauce, mozzarella, grilled chicken, red onions, and cilantro.",
                    price = 1699.99
                ),
                Items(
                    id = 55,
                    title = "Hawaiian Bliss",
                    imageUrl = "https://img.cdn4dd.com/p/fit=cover,width=1200,height=1200,format=auto,quality=90/media/photosV2/143d3000-2f0b-4aeb-abc4-731e155de7a9-retina-large.jpg",
                    description = "Tomato sauce, mozzarella, ham, pineapple, and bacon.",
                    price = 1799.99
                ),
                Items(
                    id = 56,
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
                    id = 61,
                    title = "Grilled Chicken Breast",
                    imageUrl = "https://assets.epicurious.com/photos/629e61c3c8fc75488633ba7d/1:1/w_4102,h_4102,c_limit/GrilledChickenBreast_RECIPE_052522_34925.jpg",
                    description = "Juicy grilled chicken breast seasoned to perfection.",
                    price = 1099.99
                ),
                Items(
                    id = 62,
                    title = "Fried Chicken Wings",
                    imageUrl = "https://www.krumpli.co.uk/wp-content/uploads/2023/08/Crispy-Fried-Buttermilk-Chicken-Wings-01.jpg",
                    description = "Crispy fried chicken wings with your choice of sauce.",
                    price = 1299.99
                ),
                Items(
                    id = 63,
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
                    id = 64,
                    title = "Spicy Buffalo Chicken Sandwich",
                    imageUrl = "https://somethingaboutsandwiches.com/wp-content/uploads/2021/03/crispy-buffalo-chicken-sandwich.jpg",
                    description = "Spicy buffalo chicken breast, lettuce, tomato, and ranch dressing on a bun.",
                    price = 1399.99
                ),
                Items(
                    id = 65,
                    title = "Honey Mustard Glazed Chicken",
                    imageUrl = "https://i2.wp.com/www.downshiftology.com/wp-content/uploads/2015/08/Baked-Honey-Mustard-Chicken-8.jpg",
                    description = "Grilled chicken glazed with honey mustard, served with rice and vegetables.",
                    price = 1499.99
                ),
                Items(
                    id = 66,
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
                    id = 71,
                    title = "Atieke Poulet",
                    imageUrl = "https://voiedefemme.net/wp-content/uploads/2021/07/cuisine.jpg",
                    description = "Description of the first Tchieke dish.",
                    price = 1299.99
                ),
                Items(
                    id = 72,
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
                    id = 73,
                    title = "Atieke Alloco",
                    imageUrl = "https://oswaldkouame.jp/wp-content/uploads/2018/02/Atti%C3%A9k%C3%A9-with-Alloco.jpg",
                    description = "Description of the first special Tchieke dish.",
                    price = 1599.99
                ),
                Items(
                    id = 74,
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

    // Supermarket items

    // TODO: Add a language translator
    val shopItemCategories = listOf(
        //ShopItemCategories
        ShopItemCategories(
            "Fruits legumes",
            description = "pommme, banane, orange, tomate, carotte, salade, concombre, poivron, aubergine, courgette, pomme de terre, oignon, ail, citron, fraise, framboise, mure, myrtille, groseille, cassis, cerise, prune, abricot, peche, nectarine, kiwi, ananas, mangue, papaye, litchi, kaki, figue, raisin, melon, pastèque, pamplemousse",
            imageUrl = "https://www.vie-publique.fr/files/styles/twitter_image/public/en_bref/image_principale/Fruits-et-l%C3%A9gumes.jpg?itok=dfE77eIk"

        ),
        ShopItemCategories(
            "Petit dejeuner",
            description = "cafe, the, chocolat, lait",

        ),
        ShopItemCategories(
            "Eau boisson",
            description = "eau mineral , jus de fruits, boisson gazeuses..."
        ),
        ShopItemCategories(
            "Aliments de base",
            description = "riz, patte, haricot, mil, mais, sorghots, semoule, couscous, quinoa, boulgour..."
        ),
        ShopItemCategories(
            "Epicerie sucree",
            description = "Cake, gateau, biscuit, chocolat, bonbon, sucre,"
        ),
        ShopItemCategories(
            "Hygiene",
            description = "dentifrice, brosse a dent, savon, gel douche, shampoing, creme, lait, coton, coton tige, mouchoir, papier toilette, serviette hygienique, tampon, couche, lingette, deodorant, parfum, maquillage, rasoir, mousse a raser, creme a raser, apres rasage, cire, epilateur, tondeuse, pince a epiler, lime a ongle, vernis a ongle, dissolvant, coton a demaquiller, eau micellaire, "
        ),
        ShopItemCategories(
            "Beaute",
            description = "pommade, masque, gel, serum, huile, lait, creme, lotion, tonique, eau micellaire, eau thermale, eau florale, eau de toilette, eau de parfum, eau de cologne, eau de soin, "
        ),
        ShopItemCategories(
            "Epicerie salee",
            description = "sel, poivre, epice, herbe, huile, vinaigre, moutarde, mayonnaise, ketchup, sauce, lentille, pois chiche, pois casses, pois, haricot, feve, flageolet, soja, tofu, tempeh, seitan, quorn, prot",
        ),
        ShopItemCategories(
            "Bebe",
            description = "couches, lingettes, lait, biberon, tetine, tasse, assiette, couvert, bavoir, chaise haute, poussette, land",
        ),
        ShopItemCategories(
            "Animaux",
            description = "croquette, pate, boite, sachet, friandise, jouet, litiere, bac, arbre, griffoir, coussin, panier, collier, laisse, harnais, museliere, cage, aquarium, terrarium, vivarium, clapier, poulailler"
        ),
        ShopItemCategories("Autres"),
    )

    private val FruitParadise =  listOf(
        ItemsList(
            title = "Fruits legumes",
            items = listOf(
                Items(
                    id = 90,
                    title = "Banane",
                    price = 150.00,
                    promotion = "10% reduction",
                    imageUrl = "https://rabbits.world/wp-content/uploads/2022/01/Banane.jpg",
                    description = "par kilo",
                ),
                Items(
                    id = 92,
                    title = "Pomme",
                    promotion = "",
                    imageUrl = "https://www.halfyourplate.ca/wp-content/uploads/2014/12/one-apple-with-leaves.jpg",
                    price = 100.00,
                    description = "2 unités",
                ),
                Items(
                    id = 93,
                    title = "Citron",
                    imageUrl = "https://laparra.fr/wp-content/uploads/2021/06/citron-1-1024x1024-1.png",
                    price = 240.00,
                    description = "500 gr"
                ),
                Items(
                    id = 94,
                    title = "Oignon",
                    imageUrl = "https://masalchi.fr/images/epice-bio/160/1616771699/big/oignon-bio-france-allium-cepa-epice-bio.jpg",
                    price = 200.00,
                    description = "500 gr"
                ),
                Items(
                    id = 95,
                    title = "Poivron",
                    imageUrl = "https://www.aprifel.com/wp-content/uploads/2019/02/poivron-vert.jpg",
                    price = 300.00,
                    description = "500 gr"
                ),
                Items(
                    id = 96,
                    title = "Piment",
                    imageUrl = "https://www.jardindupicvert.com/media/catalog/product/cache/9b8db22e5cb5a580d94445e657d02e33/2/6/26575-1-capsicum_frutescens_penis_ou_peter_pepper.jpg",
                    price = 100.00,
                    description = "500 gr"
                ),
            )
        ),
    )

    private val koMarket = listOf(
        ItemsList(
            title = "Fruits legumes",
            items = listOf(
                Items(
                    id = 100,
                    title = "Banane",
                    price = 200.00,
                    promotion = "10% reduction",
                    imageUrl = "https://www.physionorm.fr/wp-content/uploads/sites/3/2023/05/Design-sans-titre.jpg",
                    description = "par kilo",
                ),
                Items(
                    id = 102,
                    title = "Pomme",
                    promotion = "",
                    imageUrl = "https://sebalafruits.com/wp-content/uploads/2021/08/Untitled-design-3-2.png",
                    price = 150.00,
                    description = "3 unités",
                ),
                Items(
                    id = 103,
                    title = "Citron",
                    imageUrl = "https://zanaromi.com/36-large_default/arome-naturel-citron-1-200.jpg",
                    price = 200.00,
                    description = "500 gr"
                ),
                Items(
                    id = 104,
                    title = "Oignon",
                    imageUrl = "https://www.magellan-bio.fr/9439-large_default/oignon-rouge-red-baron.jpg",
                    price = 200.00,
                    description = "500 gr"
                ),
                Items(
                    id = 105,
                    title = "Poivron",
                    imageUrl = "https://www.academiedugout.fr/images/8383/1200-auto/fotolia_52283514_subscription_xl-copy.jpg?poix=50&poiy=50",
                    price = 200.00,
                    description = "500 gr"
                ),
                Items(
                    id = 106,
                    title = "Piment",
                    imageUrl = "https://media.istockphoto.com/id/931936960/fr/vectoriel/%C3%B0-%C3%B0-%C3%B1-%C3%B0-%C3%B0-%C3%B1-%C3%B0%C2%B5%C3%B1-%C3%B0-%C3%B0%C2%B5%C3%B1-%C3%B0.jpg?s=612x612&w=0&k=20&c=wt2PAUAo58N5I38yfYul4ZfxA0c41r0tq9b1eBjhjRU=",
                    price = 200.00,
                    description = "500 gr"
                ),

                )
        ),
        ItemsList(
            title = "Petit dejeuner",
            items = listOf(
                Items(
                    id = 107,
                    title = "Lait",
                    promotion = "buy 1 get 1 free" ,
                    imageUrl = "https://fondationolo.ca/wp-content/uploads/2017/03/fondation-olo-infographie-lait-v2.jpg",
                    price = 1000.00,
                    description = "1 litre",
                ),
                Items(
                    id = 108,
                    title = "Oeufs",
                    imageUrl = "https://mlujw6er8qda.i.optimole.com/w:1024/h:683/q:mauto/ig:avif/f:best/https://mag.qilibri.fr/wp-content/uploads/2020/09/oeuf.jpg",
                    price = 1000.00,
                    description = "12 unités",
                ),
                Items(
                    id = 109,
                    title = "Yaourt",
                    imageUrl = "https://www.mesproduitsfermiers.com/1272-large_default/yaourt-a-la-fraise-par-4.jpg",
                    price = 1500.00,
                    description = "4 unités"
                ),
                Items(
                    id = 110,
                    title = "Cafe",
                    imageUrl = "https://m.media-amazon.com/images/I/61NpIMFTVZL._AC_UF1000,1000_QL80_.jpg",
                    price = 2000.00,
                    description = "500 gr"
                ),
                Items(
                    id = 111,
                    title = "The",
                    imageUrl = "https://bamarketllc1.com/cdn/shop/files/gps_generated.png?v=1702339125&width=416",
                    price = 100.00,
                    description = "50 gr"
                ),
            )
        ),
        ItemsList(
            title = "Eau boisson",
            items = listOf(
                Items(
                    id = 112,
                    title = "Diago",
                    imageUrl = "https://sodishop.bmperp.com/assets/uploads/produits/eau-mineral-diago-naturel-05-litre-ftm00228-8836.jpg",
                    price = 500.00,
                    description = "1 litre",
                ),
                Items(
                    id = 113,
                    title = "Jus Precia",
                    imageUrl = "https://ci.jumia.is/unsafe/fit-in/500x500/filters:fill(white)/product/18/956941/1.jpg?7165",
                    price = 1000.00,
                    description = "1 litre",
                ),
                Items(
                    id = 114,
                    title = "Fanta",
                    imageUrl = "https://market-product-images-cdn.getirapi.com/product/662adfff-67f4-4311-88c9-bfecab9d8976.jpg",
                    price = 1000.00,
                    description = "1 litre",
                ),
            )
        ),
        ItemsList(
            title = "Aliments de base",
            items = listOf(
                Items(
                    id = 115,
                    title = "Riz",
                    imageUrl = "https://images.radio-canada.ca/v1/alimentation/ingredient/1x1/riz-basmati-ingredients-mordu.jpg",
                    price = 1000.00,
                    description = "1 kg",
                ),
                Items(
                    id = 116,
                    title = "Pate",
                    imageUrl = "https://media.carrefour.fr/medias/ecef297c67bf3a44ba81bebb871b8fcf/p_540x540/03038350023605-a1n1-s01.jpg",
                    price = 1000.00,
                    description = "1 kg",
                ),
                Items(
                    id = 117,
                    title = "Haricot",
                    imageUrl = "https://www.greenvrac.com/wp-content/uploads/2021/03/grossiste-haricot-niebe-pas-cher-en-vrac.jpg",
                    price = 1000.00,
                    description = "1 kg",
                ),
            )
        ),
        ItemsList(
            title = "Epicerie sucree",
            items = listOf(
                Items(
                    id = 118,
                    title = "Gateau",
                    imageUrl = "https://www.mayrand.ca/globalassets/mayrand/catalog-mayrand/surgele/11662-gateau-chocolat-510-g-mccain.jpg",
                    price = 1400.00,
                    description = "1 unité",
                ),
                Items(
                    id = 119,
                    title = "Biscuit",
                    imageUrl = "https://bakingwithgranny.co.uk/wp-content/uploads/2022/03/Hungarian-Chocolate-3.jpg",
                    price = 1250.00,
                    description = "1 paquet",
                ),
                Items(
                    id = 120,
                    title = "Chocolat",
                    imageUrl = "https://www.jeffdebruges-marrakech.com/cdn/shop/products/Sanstitre-2022-01-21T165737.321.png?v=1642780680",
                    price = 900.00,
                    description = "1 tablette",
                ),
            )
        ),


        )

    private val sokoMarket = listOf(
        ItemsList(
            title = "Viande Blanche",
            items = listOf(
                Items(
                    id = 122,
                    title = "Poulet",
                    imageUrl = "https://img-3.journaldesfemmes.fr/vFEM-3POiKT8i8NmZvqwIZiG9kg=/1500x/smart/1a712856aaaf419dbfa5d24cc9808e03/ccmcms-jdf/35925017.jpg",
                    price = 2500.00,
                    description = "2 kg",
                ),
                Items(
                    id = 121,
                    title = "Poulet Cuisse",
                    imageUrl = "https://halalfs.com/960-large_default/cuisse-de-poulet-congele-10kg.jpg",
                    price = 2500.00,
                    description = "2 kg",
                ),
            )
        ),
        ItemsList(
            title = "Viande Rouge",
            items = listOf(
                Items(
                    id = 123,
                    title = "Bonfilet",
                    imageUrl = "https://images.migrosone.com/sanalmarket/product/25030258/25030258-64ffbc-1650x1650.jpg",
                    price = 1000.00,
                    description = "400 gr",
                ),
                Items(
                    id = 124,
                    title = "viande de boeuf",
                    imageUrl = "https://www.academiedugout.fr/images/9857/1200-auto/boeuf_000.jpg?poix=50&poiy=50",
                    price = 5000.00,
                    description = "1 kg",
                ),
                Items(
                    id = 125,
                    title = "viande hachee",
                    imageUrl = "https://img.cuisineaz.com/680x357/2017/11/25/i134409-viande-hachee.jpeg",
                    price = 5000.00,
                    description = "1 kg",
                ),
            )
        ),
    )

    private val MaliBooks = listOf(
        ItemsList(
            title = "1ere Annee",
            items = listOf(
                Items(
                    id = 132,
                    title = "Mamadou et Bineta",
                    imageUrl = "https://upload.wikimedia.org/wikipedia/commons/d/d2/Mamadou_et_Bineta%28nouveau_syllabaire_%29.jpg",
                    price = 2500.00,
                    description = "",
                ),
                Items(
                    id = 133,
                    title = "Bescherelle",
                    imageUrl = "https://images.epagine.fr/369/9782401052369_1_75.jpg",
                    price = 1000.00,
                    description = "",
                ),
            )
        ),
        ItemsList(
            title = "2eme Annee",
            items = listOf(
                Items(
                    id = 134,
                    title = "Livre de lecture",
                    imageUrl = "https://enseignants.nathan.fr/sites/default/files/ouvrage/9782091573311.JPG",
                    price = 5000.00,
                    description = "",
                ),
                Items(
                    id = 135,
                    title = "Livre de mathematique",
                    imageUrl = "https://assets.lls.fr/books/978-2-37760-147-9-MA2_P_2019.png",
                    price = 5000.00,
                    description = "",
                ),
            )
        ),
    )

    val shopItems = listOf(
        BusinessItems(
            businessId = TestObjects.shops[0].id,
            items = FruitParadise,
            isRestaurant = false
        ),
        BusinessItems(
            businessId = TestObjects.shops[1].id,
            items = koMarket,
            isRestaurant = false
        ),
        BusinessItems(
            businessId = TestObjects.shops[2].id,
            items = sokoMarket,
            isRestaurant = false
        ),
        BusinessItems(
            businessId = TestObjects.shops[4].id,
            items = MaliBooks,
            isRestaurant = false
        ),

    )

}