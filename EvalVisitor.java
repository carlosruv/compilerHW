import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class EvalVisitor  extends CalcBaseVisitor<Integer> {
	
	//Map<String, Integer> memory = new HashMap<String, Integer>();
	private HashMap<String, Integer> memory = new HashMap<String, Integer>();


	   public Integer visitMul(CalcParser.MulContext ctx) {
           int left = visit(ctx.expr(0));
           int right = visit(ctx.expr(1));
   
           
                   return left * right;
          
   }

   public Integer visitInt(CalcParser.IntContext ctx) {
           return Integer.valueOf(ctx.INT().getText());
   }

   public Integer visitPrintExpr(CalcParser.PrintExprContext ctx) {
           Integer value = visit(ctx.expr());
           System.out.println(value);
           return 0;
   }
   
	@Override
	public Integer visitProg(CalcParser.ProgContext ctx) {
		 List<CalcParser.StatContext> stats = ctx.stat();
	        Integer value = null;
	        for (int i = 0; i < stats.size(); i++) {
	            CalcParser.StatContext stat = stats.get(i);
	            value = stat.accept(this);
	        }
	        return value;
	    }
	

	
	@Override
	public  Integer visitDeclaration(CalcParser.DeclarationContext ctx) {
		String id = ctx.declare().getText();
		if (memory.containsKey(id)) {
			System.out.println("variable " + id + " is already declared ");
		}
		Integer value = visit(ctx.declare());
		memory.put(id, value);
		return value;
		}

	
	

	
	@Override
	public Integer visitAssign(CalcParser.AssignContext ctx) {
		String id = ctx.ID().getText();
	Integer value = ctx.expr().accept(this);
	memory.put(id, value);
	return value;
	}

	
	@Override
	public Integer visitBlank(CalcParser.BlankContext ctx) {
        return 0;
	}

	
	@Override
	public Integer visitIntDeclation(CalcParser.IntDeclationContext ctx) {
		String id = ctx.ID().getText();
		if (memory.containsKey(id)) {
			System.out.println("variable " + id + " is already declared ");
		}
		Integer value = visit(ctx.ID());
		memory.put(id, value);
		return value;	}

	
	@Override
	public Integer visitDiv(CalcParser.DivContext ctx) {
		int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));

        
                return left / right;	}

	
	@Override
	public Integer visitAdd(CalcParser.AddContext ctx) {
		int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));

        
                return left + right;
		
	}

	
	@Override
	public Integer visitSub(CalcParser.SubContext ctx) {
		int left = visit(ctx.expr(0));
        int right = visit(ctx.expr(1));

        
                return left - right;	}

	
	@Override
	public Integer visitParens(CalcParser.ParensContext ctx) {
		return visit(ctx.expr());
	}

	
	

	
	@Override
	public Integer visitId(CalcParser.IdContext ctx) {
		Integer value = memory.get(ctx.getText());
		if (value == null) {
			return 0;

		} else {
			return Integer.parseInt(value.toString());
		}
		}
	
	

	
	

}
