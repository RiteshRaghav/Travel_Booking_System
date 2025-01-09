<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="media.css">
    <title>Travel Agency Website</title>
<style> .cards { position: relative; } .cards .popup { display: none; position: absolute; top: 10px; left: 10px; background-color: rgba(0, 0, 0, 0.8); color: white; padding: 10px; border-radius: 5px; width: 200px; z-index: 1; } .card:hover .popup { display: block; }
</style>
</head>

<body>
    <header>
        <nav>
            <h4>WonderWave Travels</h4>
            <ul id="menu_bx">
                <li><a href="#discover">Discover</a></li>
                <li><a href="footer">Contact us</a></li>
                <li><a href="#">Special Deals</a></li>
                <li><a href="#about">About US</a></li>
                <li><a href="signup.jsp">Register</a></li>
                <li><a href="login.jsp">login</a></li>

            </ul>
            <i class="bi bi-three-dots"></i>
        </nav>
        <div class="content">
            <div class="cont_bx">
                <h1>The right desitination for you and your family</h1>
                <p>Creative taglines have the capability of capturing the attention of potential custumer.</p>
                <button>Start your search</button>
            </div>
            <div class="trip_bx">
                <div class="search_bx">
                    <div class="card">
                        <h4>Location <i class="bi bi-caret-down-fill"></i></h4>
                        <input type="text" placeholder="Enter your destination">
                    </div>
                    <div class="card">
                        <h4>Date <i class="bi bi-caret-down-fill"></i></h4>
                        <input type="date">
                    </div>
                    <div class="card">
                        <h4>Pepole <i class="bi bi-caret-down-fill"></i></h4>
                        <input type="number" placeholder="How many People?">
                    </div>
                    <input type="button" value="Explore Now">
                </div>
                <div class="travel_bx">
                    <h4>Countries to travel</h4>
                    <div class="cards">
                        <div class="card">
                            <h3>INDIA <img src="icon/india.png" alt=""></h3>
                            <img src="img/Mumbai-India-at-night.jpg" alt="">
                            <div class="btn_city">
                                <a href="India.jsp">Read Now</a>
                                
                                <h5>Mumbai Central <br> <span>8.6k</span></h5>
                            </div>
                        </div>
                        <div class="card">
                            <h3>UNITED STATE <img src="icon/united-states.png" alt=""></h3>
                            <img src="img/newyork.webp" alt="">
                            <div class="btn_city">
                                <a href="US.jsp">Read Now</a>
                                <h5>NewYork <br> <span>87k</span></h5>
                            </div>
                        </div>
                        <div class="card">
                            <h3>RUSSIA <img src="icon/russia.png" alt=""></h3>
                            <img src="img/sanpitersburg.jpg" alt="">
                            <div class="btn_city">
                                <a href="Russia.jsp">Read Now</a>
                                <h5>Sanpitersburg <br> <span>66k</span></h5>
                            </div>
                        </div>
                        <div class="card">
                            <h3>SPAIN <img src="icon/spain.png" alt=""></h3>
                            <img src="img/barcelona.jpg" alt="">
                            <div class="btn_city">
                                <a href="Spain.jsp">Read Now</a>
                                <h5>Barcelona <br> <span>73k</span></h5>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
   <div class="offers" id="discover">
    <h1>Best tour Package offers for You</h1>
    <p>Choose your next destination</p>
    <div class="cards">
        <!-- Lotus Temple Card -->
        <div class="card" data-link="Journey">
            <h3>Lotus-Dehli</h3>
            <div class="img_text">
                <img src="img/lotus_temple.jpg" alt="Lotus Temple">
                <h4>Included: Air ticket, Hotel, Breakfast, Tours, Airport Transfer</h4>
            </div>
            <div class="cont_bx">
                <div class="price">
                    <div class="heart_chat">
                        <i class="bi bi-heart-fill"><span>86415</span></i>
                        <i class="bi bi-chat-fill"><span>4586</span></i>
                    </div>
                    <div class="info_price">
                        <a href="Journey">More Info</a>
                        <h4>$2648</h4>
                    </div>
                </div>
                <div class="dayes">5 Days <br> India</div>
            </div>
        </div>

        <!-- Burj Khalifa Card -->
        <div class="card" data-link="Journey">
            <h3>Burj Khalifa-DXB</h3>
            <div class="img_text">
                <img src="img/burjkhlifa.jpg" alt="Burj Khalifa">
                <h4>Included: Air ticket, Hotel, Breakfast, Tours, Airport Transfer</h4>
            </div>
            <div class="cont_bx">
                <div class="price">
                    <div class="heart_chat">
                        <i class="bi bi-heart-fill"><span>86415</span></i>
                        <i class="bi bi-chat-fill"><span>4586</span></i>
                    </div>
                    <div class="info_price">
                        <a href="Journey">More Info</a>
                        <h4>$2648</h4>
                    </div>
                </div>
                <div class="dayes">5 Days <br> Dubai</div>
            </div>
        </div>

        <!-- Pyramids Card -->
        <div class="card" data-link="Journey">
            <h3>Pyramids-Egypt</h3>
            <div class="img_text">
                <img src="img/piramids.jpg" alt="Pyramids">
                <h4>Included: Air ticket, Hotel, Breakfast, Tours, Airport Transfer</h4>
            </div>
            <div class="cont_bx">
                <div class="price">
                    <div class="heart_chat">
                        <i class="bi bi-heart-fill"><span>86415</span></i>
                        <i class="bi bi-chat-fill"><span>4586</span></i>
                    </div>
                    <div class="info_price">
                        <a href="Journey">More Info</a>
                        <h4>$2648</h4>
                    </div>
                </div>
                <div class="dayes">7 Days <br> Egypt</div>
            </div>
        </div>

        <!-- Vietnam Card -->
        <div class="card" data-link="Journey">
            <h3>Mountain-Vietnam</h3>
            <div class="img_text">
                <img src="img/mountain.jpg" alt="Vietnam Mountains">
                <h4>Included: Air ticket, Hotel, Breakfast, Tours, Airport Transfer</h4>
            </div>
            <div class="cont_bx">
                <div class="price">
                    <div class="heart_chat">
                        <i class="bi bi-heart-fill"><span>86415</span></i>
                        <i class="bi bi-chat-fill"><span>4586</span></i>
                    </div>
                    <div class="info_price">
                        <a href="Journey">More Info</a>
                        <h4>$2648</h4>
                    </div>
                </div>
                <div class="dayes">7 Days <br> Vietnam</div>
            </div>
        </div>
    </div>
</div>

    <div class="destination">
        <div class="des_bx">
            <h4>Our Destination</h4>
            <p>choose your next Destination</p>
            <li>India</li>
            <li>Dubai</li>
            <li>USA</li>
            <li>Vietnam</li>
            <li>Russia</li>
            <li>Brazil</li>
            <h6>Included: Air ticket, Hotel, Breakfast, Tours, Airport transfer</h6>
            <button>MORE INFO</button>
        </div>
        <div class="img_bx">
            <img src="icon/Main_plan.png" alt="">
            <div class="msg">
                <img src="icon/round_india_flag.png" alt="">
                <div class="cont">
                    <h4>India</h4>
                    <div class="icon">
                        <i class="bi bi-heart-fill"><span>86415</span></i>
                        <i class="bi bi-chat-fill"><span>4586</span></i>
                    </div>
                </div>
            </div>
            <div class="msg">
                <img src="icon/united-states.png" alt="">
                <div class="cont">
                    <h4>United State</h4>
                    <div class="icon">
                        <i class="bi bi-heart-fill"><span>86415</span></i>
                        <i class="bi bi-chat-fill"><span>4586</span></i>
                    </div>
                </div>
            </div>
        </div>
    </div>


     <!-- about -->
    <div class="about" id="about">
        <h1>About Us</h1>

        <h2>Welcome to WonderWave Travels!</h2>
        <p>At WonderWave Travels, we believe that every journey is a unique opportunity to explore, discover, and connect with the world around us. Founded with a passion for travel and a deep understanding of what makes a trip memorable, we are here to turn your travel dreams into reality.</p>

       
        <h2>Why Choose Us?</h2>
        <ul>
            <li><strong>Personalized Service</strong>: We listen to your travel preferences and craft a trip that matches your style, interests, and budget.</li>
            <li><strong>Expert Knowledge</strong>: Our team of experienced travel professionals has in-depth knowledge of destinations, hidden gems, and travel tips to ensure a seamless journey.</li>
            <li><strong>Trustworthy and Reliable</strong>: We work with a network of trusted partners to offer you the best deals on flights, accommodations, and experiences.</li>
            <li><strong>24/7 Support</strong>: No matter where you are, we’re always just a phone call or email away to assist you with any questions or concerns.</li>
        </ul>
    </div>    

    <footer>
        <h2>Subscribe <br> get special discount</h2>
        <p>* Terms and Conditions applied. </p>
        <div class="input">
            <input type="text" placeholder="Enter your Email Address">
            <button>Subscribe</button>
        </div>
        <ul>
            <li>7823 <br> <h6>Years Serving the travel Industry</h6></li>
            <li>6374 <br><h6>Global <br> Patnership</h6> </li>
            <li>1496 <br> <h6>Industry Awards since 2022</h6></li>
            <li>5658 <br> <h6>Subscribe</h6></li>
        </ul>
    </footer>
   <script>
document.querySelectorAll('.card').forEach(card => {
    card.addEventListener('click', () => {
        const link = card.getAttribute('data-link');
        if (link) {
            window.location.href = "/TravelBooking/Journey";
        }
    });
});
</script>

    
</body>

</html>
