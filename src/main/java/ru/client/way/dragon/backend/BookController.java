package ru.client.way.dragon.backend;

//@RestController
//@RequestMapping("/api/book")
//public class BookController {
//
//    @Autowired
//    private BookRepository repository;
//
//    @GetMapping("/{id}")
//    public Book findById(@PathVariable long id) {
//        return repository.findById(id)
//                .orElseThrow(() -> new BookNotFoundException());
//    }
//
//    @GetMapping("/")
//    public Collection<Book> findBooks() {
//        return repository.getBooks();
//    }
//
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.OK)
//    public Book updateBook(
//            @PathVariable("id") final String id, @RequestBody final Book book) {
//        return book;
//    }
//}
