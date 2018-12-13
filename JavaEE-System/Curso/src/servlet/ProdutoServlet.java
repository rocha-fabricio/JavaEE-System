package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Produto;
import dao.DaoProduto;

/**
 * Servlet implementation class Produto
 */
@WebServlet("/salvarProduto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoProduto produtoDao = new DaoProduto();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProdutoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String acao = request.getParameter("acao");
			String produto = request.getParameter("produto");
			String id = request.getParameter("id");

			if (acao.equals("delete")) {
				produtoDao.delete(Integer.parseInt(id));

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", produtoDao.listar());
				view.forward(request, response);
			} else if (acao.equals("editar")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				Produto prod = produtoDao.consultar(Integer.parseInt(id));
				request.setAttribute("produto", prod);
				view.forward(request, response);
			} else if (acao.equals("listarTodos")) {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", produtoDao.listar());
				view.forward(request, response);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");

		if (acao != null && acao.equals("reset")) {
			try {
				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", produtoDao.listar());
				view.forward(request, response);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {

			String id = request.getParameter("id");
			String nome = request.getParameter("nome");
			String qtd = request.getParameter("quantidade");
			String valor = request.getParameter("valor");

			Produto produto = new Produto();
			produto.setId(!id.isEmpty() ? Integer.parseInt(id) : 0);
			produto.setNome(nome);
			produto.setQuantidade(Double.parseDouble(qtd));
			produto.setValor(Double.parseDouble(valor));
			
			try {

				if (id == null || id.isEmpty()) {
					produtoDao.salvar(produto);
				} else {
					produtoDao.atualizar(produto);
				}

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produtos", produtoDao.listar());
				view.forward(request, response);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

