package web.Controller;

import jakarta.servlet.http.HttpSession;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import web.DTO.ProductDTO;
import web.Model.User;
import web.Service.ColorService;
import web.Service.ProductService;
import web.Service.StatusService;
import web.Service.SupplierService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import web.DTO.ProductColorDTO;
import web.Model.Product;
import web.Model.ProductColor;
import web.Model.ProductColorPK;
import web.Model.Status;
import web.Model.Supplier;
import web.Service.FileService;
import web.Service.ProductColorService;

@Controller
public class ManageProductController {

    @Autowired
    private ProductService proServ;
    @Autowired
    private HttpSession session;
    @Autowired
    private StatusService statusServ;
    @Autowired
    private SupplierService supplierServ;
    @Autowired
    private ColorService colorServ;
    @Autowired
    private FileService fileServ;
    @Autowired
    private ProductColorService proColorServ;

    @GetMapping("admin/product-management/view")
    public String ViewAllProduct(Model model) {
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1 && user.getRole().getRoleID() != 2) {
            return "account/view-role-not-permission";
        } else {
            model.addAttribute("ListProduct", proServ.findAllProduct());
            return "product/view-manage-product";
        }
    }

    //Nhận yêu cầu ngừng kinh doanh sản phẩm
    @PostMapping("admin/product-management/lock")
    public String LockProduct(@RequestParam("id") Integer proID) {
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1 && user.getRole().getRoleID() != 2) {
            return "account/view-role-not-permission";
        } else {
            proServ.lockById(proID);
            return "redirect:view";
        }
    }

    //Nhận yêu cầu tiếp tục kinh doanh sản phẩm
    @PostMapping("admin/product-management/unlock")
    public String UnlockProduct(@RequestParam("id") Integer proID) {
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1 && user.getRole().getRoleID() != 2) {
            return "account/view-role-not-permission";
        } else {
            proServ.unlockById(proID);
            return "redirect:view";
        }
    }

    //Hiển thị trang sửa sản phẩm
    @GetMapping("admin/product-management/edit/{id}")
    public String ViewEditProduct(Model model, @PathVariable("id") Integer productId) {
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1 && user.getRole().getRoleID() != 2) {
            return "account/view-role-not-permission";
        } else {
            model.addAttribute("Product", proServ.getProductById(productId));
            model.addAttribute("ListStatus", statusServ.findAll());
            model.addAttribute("ListSupplier", supplierServ.getAllSupplier());
            model.addAttribute("ListColor", colorServ.findAllColor());
            return "product/view-manage-edit-product";
        }
    }

    //Nhận yêu cầu sửa sản phẩm
    @PostMapping("admin/product-management/edit/{id}")
    @ResponseBody
    public String EditProduct(Model model,
            @PathVariable("id") Integer productId,
            @RequestBody ProductDTO newProductDTO) {
        JSONObject response = new JSONObject();
        try {
            //Kiểm tra quyền
            User user = (User) session.getAttribute("ADMIN");
            if (user == null) {
                return "redirect:/admin/login";
            } else if (user.getRole().getRoleID() != 1 && user.getRole().getRoleID() != 2) {
                return "account/view-role-not-permission";
            } else {
                Product oldProduct = proServ.getProductById(productId);
                oldProduct.setName(newProductDTO.getName());
                oldProduct.setPrice(newProductDTO.getPrice());
                oldProduct.setDescription(newProductDTO.getDescription());
                oldProduct.setSpecifications(newProductDTO.getSpecifications());
                oldProduct.setSupplier(new Supplier(newProductDTO.getSupplier()));
                oldProduct.setStatus(new Status(newProductDTO.getStatus()));
                oldProduct.setUpdateAt(LocalDateTime.now());
                //Lưu danh sách màu của sản phẩm
                List<ProductColor> listOldProColor = oldProduct.getProductColorList();
                List<ProductColor> listNewProColor = new ArrayList<>();
                for (ProductColorDTO proColorDTO : newProductDTO.getColorList()) {
                    //Tạo PK
                    ProductColorPK id = new ProductColorPK();
                    id.setProductID(oldProduct.getProductID());
                    id.setColorID(proColorDTO.getColor());
                    ProductColor oldProductColor = proColorServ.checkExitsPKInListProductColor(listOldProColor, id);
                    if(oldProductColor != null){
                        oldProductColor.setQuantity(proColorDTO.getQuantity());
                        String base64Image = proColorDTO.getImage();
                        if(!base64Image.isEmpty()){
                            String fileName = oldProduct.getProductID() +"-"+ proColorDTO.getColor() + ".png";
                            fileServ.saveFileWithBase64String(base64Image, fileName);
                            oldProductColor.setImages("images/" + fileName);
                        }
                        listNewProColor.add(oldProductColor);
                    }else{
                        //Tạo ProductColor mới
                        ProductColor proColor = new ProductColor();
                        proColor.setProductColorPK(id);
                        proColor.setQuantity(proColorDTO.getQuantity());
                        proColor.setStatus(new Status(1));
                        //Lưu hình ảnh
                        String base64Image = proColorDTO.getImage();
                        String fileName = oldProduct.getProductID() +"-"+ proColorDTO.getColor() + ".png";
                        fileServ.saveFileWithBase64String(base64Image, fileName);
                        proColor.setImages("images/" + fileName);
                        listNewProColor.add(proColor);
                    }                   
                }
                oldProduct.setProductColorList(listNewProColor);
                proServ.saveProduct(oldProduct);  
                response.put("Success", true);
                return response.toString();
            }
        } catch (Exception e) {
            response.put("Success", false);
            return response.toString();
        }
    }
     
    //Hiển thị trang thêm sản phẩm
    @GetMapping("admin/product-management/add")
    public String ViewAddProduct(Model model) {
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1 && user.getRole().getRoleID() != 2) {
            return "account/view-role-not-permission";
        } else {
            model.addAttribute("ListStatus", statusServ.findAll());
            model.addAttribute("ListSupplier", supplierServ.getAllSupplier());
            model.addAttribute("ListColor", colorServ.findAllColor());
            return "product/view-manage-add-product";
        }
    }

    //Nhận yêu cầu thêm sản phẩm
    @RequestMapping("admin/product-management/add")
    @ResponseBody
    public String AddAccount(@RequestBody ProductDTO newProductDTO) {
        JSONObject response = new JSONObject();
        try {
            //Kiểm tra quyền
            User user = (User) session.getAttribute("ADMIN");
            if (user == null) {
                return "redirect:/admin/login";
            } else if (user.getRole().getRoleID() != 1) {
                return "account/view-role-not-permission";
            } else {
                //Lưu sản phẩm mới
                Product newProduct = new Product();
                newProduct.setName(newProductDTO.getName());
                newProduct.setPrice(newProductDTO.getPrice());
                newProduct.setDescription(newProductDTO.getDescription());
                newProduct.setSpecifications(newProductDTO.getSpecifications());
                newProduct.setSupplier(new Supplier(newProductDTO.getSupplier()));
                newProduct.setStatus(new Status(newProductDTO.getStatus()));
                newProduct.setCreateAt(LocalDateTime.now());
                newProduct.setUpdateAt(LocalDateTime.now());
                Product productCreated = proServ.saveProduct(newProduct);

                //Lưu danh sách màu của sản phẩm
                List<ProductColor> listNewProColor = new ArrayList<>();
                for (ProductColorDTO proColorDTO : newProductDTO.getColorList()) {
                    //Tạo PK
                    ProductColorPK id = new ProductColorPK();
                    id.setProductID(productCreated.getProductID());
                    id.setColorID(proColorDTO.getColor());
                    //Tạo ProductColor
                    ProductColor proColor = new ProductColor();
                    proColor.setProductColorPK(id);
                    proColor.setQuantity(proColorDTO.getQuantity());
                    proColor.setStatus(new Status(1));
                    String base64Image = proColorDTO.getImage();
                    //Lưu hình ảnh
                    String fileName = productCreated.getProductID() +"-"+ proColorDTO.getColor() + ".png";
                    fileServ.saveFileWithBase64String(base64Image, fileName);
                    proColor.setImages("images/" + fileName);
                    listNewProColor.add(proColor);
                }
                proColorServ.saveListProductColor(listNewProColor);
                response.put("Success", true);
                return response.toString();
            }
        } catch (Exception e) {
            response.put("Success", false);
            return response.toString();
        }
    }
    
    //Nhận yêu cầu ngừng kinh doanh màu của sản phẩm
    @PostMapping("admin/product-management/lock-color/{proId}")
    public String LockProductColor(@PathVariable("proId") Integer proID,
            @RequestParam("id") Integer colorId) {
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1 && user.getRole().getRoleID() != 2) {
            return "account/view-role-not-permission";
        } else {
            ProductColorPK id = new ProductColorPK(proID, colorId);
            proColorServ.lockById(id);
            return "redirect:../edit/"+proID;
        }
    }

    //Nhận yêu cầu tiếp tục kinh doanh màu của sản phẩm
    @PostMapping("admin/product-management/unlock-color/{proId}")
    public String UnlockProductColor(@PathVariable("proId") Integer proID,
            @RequestParam("id") Integer colorId) {
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1 && user.getRole().getRoleID() != 2) {
            return "account/view-role-not-permission";
        } else {
            ProductColorPK id = new ProductColorPK(proID, colorId);
            proColorServ.unlockById(id);
            return "redirect:../edit/"+proID;
        }
    }
    
    //Tifm kiếm
    @GetMapping("admin/product-management/search")
    public String SearchProduct(Model model, @RequestParam("keyword") String keyword) {
        //Kiểm tra quyền
        User user = (User) session.getAttribute("ADMIN");
        if (user == null) {
            return "redirect:/admin/login";
        } else if (user.getRole().getRoleID() != 1 && user.getRole().getRoleID() != 2) {
            return "account/view-role-not-permission";
        } else {
            model.addAttribute("ListProduct", proServ.searchProductInManage(keyword));
            return "product/view-manage-product";
        } 
    }
}
